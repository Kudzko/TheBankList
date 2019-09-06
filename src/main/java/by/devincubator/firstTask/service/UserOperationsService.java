package by.devincubator.firstTask.service;

import by.devincubator.firstTask.bean.Account;
import by.devincubator.firstTask.bean.User;
import by.devincubator.firstTask.model.dao.daoexception.DAOException;
import by.devincubator.firstTask.model.dao.daoimplimintation.AccountDAO;
import by.devincubator.firstTask.model.dao.daoimplimintation.UserDAO;

import java.sql.Connection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserOperationsService extends AbstractService {

    public User findRichestUser() {
        User richestUser = null;
        Connection connection = connectionPool.getConnection();
        Account account = findMaxAccount(connection);
        richestUser = getUserById(connection, account.getUserId());
        return richestUser;
    }

    public Account findMaxAccount(Connection connection) {
        Account maxAccount = null;
        AccountDAO accountDAO = null;

        Comparator<Account> comparator = new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getAccount() - o2.getAccount();
            }
        };

        try {
            accountDAO = (AccountDAO) factoryDAO.getDAO(AccountDAO.class);
            accountDAO.setConnection(connection);
            maxAccount = Collections.max(accountDAO.getAll(), comparator);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(accountDAO
                    .releaseConnectionFromDAO());
        }
        return maxAccount;
    }

    public User getUserById(Connection connection, Integer id) {
        UserDAO userDAO = null;
        User user = null;
        try {
            userDAO = (UserDAO) factoryDAO.getDAO(UserDAO.class);
            userDAO.setConnection(connection);
            user = userDAO.getByPK(id);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(userDAO.releaseConnectionFromDAO());
        }
        return user;
    }


    public Integer getSumAllAccounts() {
        Connection connection = connectionPool.getConnection();
        Integer sum = 0;
        AccountDAO accountDAO = null;
        try {
            accountDAO = (AccountDAO) factoryDAO.getDAO(AccountDAO.class);
            accountDAO.setConnection(connection);
            sum = countSum(accountDAO.getAll());
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(accountDAO
                    .releaseConnectionFromDAO());
        }
        return sum;
    }

    private Integer countSum(List<Account> accounts) {
        Integer sum = 0;
        for (Account account : accounts) {
            sum += account.getAccount();
        }
        return sum;
    }

}
