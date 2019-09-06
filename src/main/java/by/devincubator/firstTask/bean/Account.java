package by.devincubator.firstTask.bean;

import java.util.Objects;

public class Account extends Entity{
    private int account;
    private int userId;

    public Account() {
    }

    public Account(int account) {
        this.account = account;
    }

    public Account(int id, int account) {
        super(id);
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null)||(this.getClass()) != o.getClass()) return false;
        Account account1 = (Account) o;
        return account == account1.account &&
                userId == account1.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(account, userId);
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
