package by.devincubator.firstTask.model.dao;

import by.devincubator.firstTask.bean.Entity;
import by.devincubator.firstTask.model.dao.daoexception.DAOException;
import by.devincubator.firstTask.model.dao.daoimplimintation.FactoryDAO;
import by.devincubator.firstTask.model.dao.daointerface.IAbstractDAO;
import by.devincubator.firstTask.model.dao.daointerface.IdDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractDAO<T extends IdDefinition, PK extends
        Integer> implements
        IAbstractDAO<T, PK> {

    protected Connection connection;
    protected FactoryDAO parantFactory = FactoryDAO.getInstance();
    private FactoryDAO factoryDAO;// to convert data to object

    //to prevent setting of
    // connection by two different threads simultaneously
    private Lock daoLock = new ReentrantLock();

    public AbstractDAO() {

    }

    public AbstractDAO(Connection connection, FactoryDAO factoryDAO) {
        this.connection = connection;
        this.factoryDAO = factoryDAO;
    }


    /**
     * Return sql query to insert record.
     * <p/>
     * INSERT INTO [Table] ([Arguments]) VALUES (?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Return sql query to update record.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Set arguments for insert.
     * Use also makePrStmtForEntity(T entity, PreparedStatement prpStmt) to
     * avoid copy past
     */
    public abstract void prepareStatementForInsert(T entity, PreparedStatement
            preparedStatement) throws SQLException;

    /**
     * Set arguments for update.
     * Use also makePrStmtForEntity(T entity, PreparedStatement prpStmt) to
     * avoid copy past
     */
    public abstract void prepareStatementForUpdate(T entity, PreparedStatement
            preparedStatement) throws SQLException;

    /**
     * Fill in fields of prepared statement by entity for create and update queries
     */
    protected abstract void makePrStmtForEntity(T entity, PreparedStatement prpStmt) throws SQLException;

    /**
     * Return sql query to delete record from DB.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Convert resultset to object
     */
    protected abstract T toEntityBody(ResultSet resultSet) throws SQLException;

    /**
     * Return sql query to select all records of objects from DB.
     * <p/>
     * SELECT * FROM [Table];
     */
    public abstract String getSelectAllQuery();

    /**
     * Return sql query to select records of object by id from DB.
     * <p/>
     * SELECT * FROM [Table] WHERE `id` = '?';;
     */
    public abstract String getSelectQueryByID();

    /**
     * Convert ResultSet and return list of appropriate objects.
     */

    public void closeStatement(Statement statement) {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return link on connection which contains DAO
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Set connection into DAO object and locks DAO
     *
     * @return
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
        daoLock.lock();
    }

    /**
     * extract connection from dao and release DAO
     *
     * @return
     */
    public Connection releaseConnectionFromDAO() {
        Connection connection = getConnection();
        this.connection = null;
        daoLock.unlock();
        return connection;
    }


    @Override
    public T persist(T entity) throws DAOException {

        if (entity.getId() != 0) {
            throw new DAOException("Entity is already persisted to DB.");
        }

        PreparedStatement preparedStatement = null;
        String SQL = getCreateQuery();

        try {

            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatementForInsert(entity, preparedStatement);
            int count;
            count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new DAOException("Created more than 1 record. (Created " +
                        "" + count + "record(s)).");
            }

            ResultSet genKey = preparedStatement.getGeneratedKeys();
            if (genKey.next()) {
                int id = genKey.getInt(1);
                ((Entity) entity).setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Can not create record into DB", e);
        } finally {
            closeStatement(preparedStatement);
        }


        return entity;
    }


    @Override
    public void update(T entity) throws DAOException {
        PreparedStatement preparedStatement = null;
        String SQL = getUpdateQuery();

        try {
            preparedStatement = connection.prepareStatement(SQL);
            prepareStatementForUpdate(entity, preparedStatement);

            int count = preparedStatement.executeUpdate();

            if (count != 1) {
                throw new DAOException("More then one record to update: " +
                        count);
            }

        } catch (SQLException e) {
            throw new DAOException("Can not update", e);
        } finally {
            closeStatement(preparedStatement);
        }

    }


    @Override
    public void delete(T entity) throws DAOException {
        String SQL = getDeleteQuery();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Can not delete from DB", e);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> listEntities = null;
        String SQL = getSelectAllQuery();
        PreparedStatement prpStatement = null;
        try {
            prpStatement = connection.prepareStatement(SQL);
            ResultSet rs = prpStatement.executeQuery();
            listEntities = toEntity(rs);
        } catch (SQLException e) {
            throw new DAOException("Can not get all objects from DB", e);
        }
        return listEntities;
    }

    @Override
    public T getByPK(PK pk) throws DAOException {
        List<T> listEntities = null;
        String SQL = getSelectQueryByID();

        try {
            PreparedStatement prpStatement = connection.prepareStatement(SQL);
            prpStatement.setLong(1, pk);

            ResultSet rs = prpStatement.executeQuery();
            listEntities = toEntity(rs);
        } catch (SQLException e) {
            throw new DAOException("Can not get instance by id", e);
        }

        if (listEntities == null || listEntities.size() == 0) {
            return null;
        }

        if (listEntities.size() > 1) {
            throw new DAOException("Received more than one record.");
        }

        return listEntities.iterator().next();
    }

    protected List<T> toEntity(ResultSet resultSet) {
        List<T> entities = new ArrayList<>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {

                    T entity = toEntityBody(resultSet);

                    entities.add(entity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return entities;
    }
}
