package com.caseysims.bankingsystemv3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController
{

    @Autowired
    AccountService accountService;





    @GetMapping("findAccount")
    public Optional <Account> findAccount(@RequestParam long id)
    {
        return accountService.getAccount(id);
    }

    @GetMapping("getTransactionHistory")
    public Optional<TransactionHistory> getTransactionHistory(@RequestParam long id)
    {
        return accountService.getHistory(id);
    }

    @PostMapping("createAccount")
    public String createAccount(@RequestBody Account account)
    {
        accountService.createAccount(account.getName());
        return "Success";
    }

    @PostMapping("deposit")
    public String deposit(@RequestParam int amount,@RequestParam long id)
    {
        if(accountService.deposit(amount,id))
        {
            return "Successful deposit of £" + amount;
        }
        else
        {
            return "Failure";
        }
    }

    @PostMapping("withdraw")
    public String withdraw(@RequestParam int amount, @RequestParam long id)
    {
        if(accountService.withdraw(amount,id))
        {
            return "Successful withdraw of £" + amount;
        }
        else
        {
            return "Failure not enough funds!";
        }
    }

    @PostMapping("transferFunds")
    public String transferFunds(@RequestParam int amount, @RequestParam long senderID, @RequestParam long recipientID)
    {
        if(accountService.transferFunds(senderID,recipientID,amount))
        {
            return "Success";
        }
        else
        {
            return "Failure";
        }
    }


}

