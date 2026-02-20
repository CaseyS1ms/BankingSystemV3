package com.caseysims.bankingsystemv3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TransactionHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionNumber;

    private String type;
    private LocalDateTime time;
    private int amount;
    @ManyToOne
    private Account account;

    public TransactionHistory(String type, LocalDateTime time, Account account,int amount)
    {
        this.type = type;
        this.time = time;
        this.account = account;
        this.amount = amount;
    }

    public TransactionHistory()
    {

    }



    public LocalDateTime getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
