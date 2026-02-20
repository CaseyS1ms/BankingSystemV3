package com.caseysims.bankingsystemv3.repository;

import com.caseysims.bankingsystemv3.entity.Account;
import com.caseysims.bankingsystemv3.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>
{
    List<TransactionHistory> findByAccount(Account account);
}
