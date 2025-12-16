package model;

import java.util.Date;

public class User {
    private int userId;
    private int balance;
    private Date creationDate;

    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
        this.creationDate = new Date();
    }

    public int getUserId() {
        return userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "User " + userId + " [Balance: " + balance + "]";
    }
}
