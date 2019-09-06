package by.devincubator.firstTask.model.dao.daoimplimintation;

import by.devincubator.firstTask.bean.User;
import by.devincubator.firstTask.model.dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User, Integer> {
    public static final String ADD_USER =
            "INSERT INTO `thebanklist`.`user` (`name`, `surname`) VALUES (?, ?);";
    public static final String UPDATE_USER =
            "UPDATE `thebanklist`.`user` SET `name`= ?, `surname`= ? WHERE " +
                    "`iduser` = ?;";
    public static final String DELETE_USER =
            "DELETE FROM `thebanklist`.`user` WHERE `iduser`=?;";
    public static final String SELECT =
            "SELECT `iduser`, `name`, `surname` FROM thebanklist.user ";
    public static final String FIND_USER_BY_ID =
            SELECT + "WHERE `iduser` = ?;";
    public static final String SELECT_USERS =
            SELECT + ";";

    @Override
    public String getCreateQuery() {
        return ADD_USER;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_USER;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_USER;
    }

    @Override
    public void prepareStatementForInsert(User entity, PreparedStatement preparedStatement) throws SQLException {
        makePrStmtForEntity(entity, preparedStatement);
    }

    @Override
    public void prepareStatementForUpdate(User entity, PreparedStatement preparedStatement) throws SQLException {
        makePrStmtForEntity(entity, preparedStatement);
        preparedStatement.setInt(3, entity.getId());
    }

    @Override
    protected void makePrStmtForEntity(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getSurName());
    }

    @Override
    protected User toEntityBody(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("iduser"));
        user.setName(resultSet.getString("name"));
        user.setSurName(resultSet.getString("surname"));

        return user;
    }

    @Override
    public String getSelectAllQuery() {
        return SELECT_USERS;
    }

    @Override
    public String getSelectQueryByID() {
        return FIND_USER_BY_ID;
    }
}
