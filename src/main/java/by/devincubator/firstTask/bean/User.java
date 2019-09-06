package by.devincubator.firstTask.bean;

import java.util.List;
import java.util.Objects;

public class User extends Entity{
    private String name;
    private String surName;
    private List<Account> accounts;

    public User() {
    }

    public User(String name, String surName, List<Account> accounts) {
        this.name = name;
        this.surName = surName;
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surName, user.surName) &&
                Objects.equals(accounts, user.accounts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surName, accounts);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> account) {
        this.accounts = account;
    }

    public void  addAccount(Account account){
        accounts.add(account);
    }
    public void  deleteAccount(Account account){
        accounts.remove(account);
    }
}
