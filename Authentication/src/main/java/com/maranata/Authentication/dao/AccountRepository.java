package com.maranata.Authentication.dao;

import com.maranata.Authentication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,String> {

    Optional<Account> findOneByUsername(String username);

}
