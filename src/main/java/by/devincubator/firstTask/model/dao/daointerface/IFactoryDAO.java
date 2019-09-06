package by.devincubator.firstTask.model.dao.daointerface;

import by.devincubator.firstTask.model.dao.AbstractDAO;
import by.devincubator.firstTask.model.dao.daoexception.DAOException;

public interface IFactoryDAO {
    interface DaoCreator {
        AbstractDAO create();
    }

    AbstractDAO getDAO(Class daoClass) throws DAOException;
}
