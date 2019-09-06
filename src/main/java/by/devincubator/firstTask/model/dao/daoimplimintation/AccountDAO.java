package by.devincubator.firstTask.model.dao.daoimplimintation;

import by.devincubator.firstTask.bean.Account;
import by.devincubator.firstTask.model.dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends AbstractDAO<Account, Integer> {
    public static final String ADD_ACCOUNT =
            "INSERT INTO `thebanklist`.`account` (`account`, `userid`) VALUES" +
                    " (?, ?);";
    public static final String UPDATE_ACCOUNT =
            "UPDATE `thebanklist`.`account` SET `account`= ?, `userid`= ? " +
                    "WHERE `idaccount` = ?;";
    public static final String DELETE_ACCOUNT =
            "DELETE FROM `thebanklist`.`account` WHERE `idaccount`=?;";
    public static final String SELECT =
            "SELECT `idaccount`, `account`, `userid` FROM thebanklist.account ";
    public static final String FIND_ACCOUNT_BY_ID =
            SELECT + "WHERE `idaccount` = ?;";
    public static final String SELECT_ACCOUNTS =
            SELECT + ";";
    @Override
    public String getCreateQuery() {
        return ADD_ACCOUNT;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ACCOUNT;
    }

    @Override
    public void prepareStatementForInsert(Account entity, PreparedStatement preparedStatement) throws SQLException {
        makePrStmtForEntity(entity, preparedStatement);
    }

    @Override
    public void prepareStatementForUpdate(Account entity, PreparedStatement preparedStatement) throws SQLException {
        makePrStmtForEntity(entity, preparedStatement);
        preparedStatement.setInt(3,entity.getId());
    }

    @Override
    protected void makePrStmtForEntity(Account entity, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setInt(1,entity.getAccount());
        preparedStatement.setInt(2,entity.getUserId());
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_ACCOUNT;
    }

    @Override
    protected Account toEntityBody(ResultSet resultSet) throws SQLException {

        Account account = new Account();

        account.setId(resultSet.getInt("idaccount"));
        account.setAccount(resultSet.getInt("account"));
        account.setUserId(resultSet.getInt("userid"));

        return account;
    }

    @Override
    public String getSelectAllQuery() {
        return SELECT_ACCOUNTS;
    }

    @Override
    public String getSelectQueryByID() {
        return FIND_ACCOUNT_BY_ID;
    }
}
