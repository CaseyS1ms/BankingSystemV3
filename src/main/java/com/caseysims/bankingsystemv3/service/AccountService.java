package com.caseysims.bankingsystemv3.service;

import com.caseysims.bankingsystemv3.entity.TransactionHistory;
import com.caseysims.bankingsystemv3.repository.TransactionHistoryRepository;
import com.caseysims.bankingsystemv3.entity.User;
import com.caseysims.bankingsystemv3.entity.Account;
import com.caseysims.bankingsystemv3.exception.AccountNotFoundException;
import com.caseysims.bankingsystemv3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService
{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;



    public void createAccount(User user)
    {
        Account account = new Account();
        account.setUser(user);
        accountRepository.save(account);
    }

    public Optional<Account> getAccount(long id)
    {
        return accountRepository.findById(id);

    }

    public List<TransactionHistory> getHistory(long id)
    {
        Optional<Account> temp = getAccount(id);
        if(temp.isEmpty())
        {
            throw new AccountNotFoundException("Account with ID " + id + " cannot be found");
        }
        Account account = temp.get();
        return transactionHistoryRepository.findByAccount(account);
    }

    public boolean deposit(int amount, long id)
    {
        Optional<Account> temp = getAccount(id);
        if(temp.isEmpty())
        {
            throw new AccountNotFoundException("Account with ID " + id + " cannot be found");
        }
        Account account = temp.get();
        int balance = account.getBalance();
        int increment = balance + amount;
        account.setBalance(increment);
        accountRepository.save(account);
        createHistory("Deposit",account, amount);
        return true;

    }

    public boolean withdraw(int amount, long id)
    {
        Optional<Account> temp = getAccount(id);
        if(temp.isEmpty())
        {
            throw new AccountNotFoundException("Account with ID " + id + " cannot be found");
        }
        Account account = temp.get();
        int balance = account.getBalance();
        if(balance < amount)
        {
            return false;
        }

        int decrement = balance - amount;
        account.setBalance(decrement);
        accountRepository.save(account);
        createHistory("Withdraw",account, amount);
        return true;
    }

    public boolean transferFunds(long senderID,long recipientID, int amount)
    {
        if (!withdraw(amount,senderID))
        {
            return false;
        }
        deposit(amount,recipientID);
        return true;

    }

    public void createHistory(String type, Account account, int amount)
    {
        TransactionHistory transactionHistory = new TransactionHistory(type,LocalDateTime.now(),account,amount);
        transactionHistoryRepository.save(transactionHistory);
    }

}
