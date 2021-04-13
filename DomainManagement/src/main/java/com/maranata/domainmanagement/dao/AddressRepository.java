package com.maranata.domainmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maranata.domainmanagement.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
