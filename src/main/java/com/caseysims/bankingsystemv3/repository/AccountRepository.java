package com.caseysims.bankingsystemv3.repository;

import com.caseysims.bankingsystemv3.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>
{

}
