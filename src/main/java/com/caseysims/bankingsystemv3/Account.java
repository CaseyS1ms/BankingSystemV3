package com.caseysims.bankingsystemv3;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Account
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private int balance;

    public Account()
    {
        this.balance = 0;
    }






    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }
}//end of Account class
