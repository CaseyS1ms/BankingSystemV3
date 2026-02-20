package com.caseysims.bankingsystemv3.repository;

import com.caseysims.bankingsystemv3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{

}
