package com.caseysims.bankingsystemv3;

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



    void createAccount(User user)
    {
        Account account = new Account();
        account.setUser(user);
        accountRepository.save(account);
    }

    Optional<Account> getAccount(long id)
    {
        return accountRepository.findById(id);

    }

    List<TransactionHistory> getHistory(long id)
    {
        Optional<Account> temp = getAccount(id);
        if(temp.isEmpty())
        {
            throw new AccountNotFoundException("Account with ID " + id + " cannot be found");
        }
        Account account = temp.get();
        return transactionHistoryRepository.findByAccount(account);
    }

    boolean deposit(int amount, long id)
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

    boolean withdraw(int amount, long id)
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

    boolean transferFunds(long senderID,long recipientID, int amount)
    {
        if (!withdraw(amount,senderID))
        {
            return false;
        }
        deposit(amount,recipientID);
        return true;

    }

    void createHistory(String type, Account account, int amount)
    {
        TransactionHistory transactionHistory = new TransactionHistory(type,LocalDateTime.now(),account,amount);
        transactionHistoryRepository.save(transactionHistory);
    }

}
