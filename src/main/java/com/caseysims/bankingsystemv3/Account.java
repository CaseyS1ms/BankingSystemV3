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

    private String name;
    private int balance;

    Account(String name)
    {
    this.name = name;
    this.balance = 0;
    }

    public Account()
    {

    }



    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}//end of Account class
