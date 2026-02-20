package com.caseysims.bankingsystemv3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>
{
    List<TransactionHistory> findByAccount(Account account);
}
