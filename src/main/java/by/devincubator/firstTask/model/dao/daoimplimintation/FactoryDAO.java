package by.devincubator.firstTask.model.dao.daoimplimintation;

import by.devincubator.firstTask.model.dao.AbstractDAO;
import by.devincubator.firstTask.model.dao.daoexception.DAOException;
import by.devincubator.firstTask.model.dao.daointerface.IFactoryDAO;

import java.util.HashMap;
import java.util.Map;

public class FactoryDAO implements IFactoryDAO {


    private static final FactoryDAO instance = new FactoryDAO();

    private Map<Class, DaoCreator> daoObjects;

    public static FactoryDAO getInstance() {
        return instance;
    }

    private FactoryDAO() {
        this.daoObjects = new HashMap<Class, DaoCreator>();

        daoObjects.put(UserDAO.class, new DaoCreator() {
            @Override
            public AbstractDAO create() {
                return new UserDAO();
            }
        });

        daoObjects.put(AccountDAO.class, new DaoCreator() {
            @Override
            public AbstractDAO create() {
                return new AccountDAO();
            }
        });

          }



    @Override
    public AbstractDAO getDAO( Class daoClass) throws DAOException {
        DaoCreator daoObject = daoObjects.get(daoClass);

        if (daoObject == null) {
            throw new DAOException("Not found DAO object for " + daoClass);
        }
        return daoObject.create();
    }



}
