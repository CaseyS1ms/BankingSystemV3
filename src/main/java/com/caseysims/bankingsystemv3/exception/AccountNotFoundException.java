package com.caseysims.bankingsystemv3.exception;

public class AccountNotFoundException extends RuntimeException
{
    public AccountNotFoundException(String message)
    {
        super(message);
    }
}

