package by.devincubator.firstTask.service;

import by.devincubator.firstTask.model.dao.ConnectionPool;
import by.devincubator.firstTask.model.dao.daoimplimintation.FactoryDAO;

public class AbstractService {
    protected FactoryDAO factoryDAO = FactoryDAO.getInstance();
    protected ConnectionPool connectionPool = ConnectionPool.getInstance();

    public AbstractService() {
    }
}
