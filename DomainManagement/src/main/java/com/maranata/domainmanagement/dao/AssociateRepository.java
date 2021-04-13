package com.maranata.domainmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maranata.domainmanagement.entity.Associate;

public interface AssociateRepository extends JpaRepository<Associate,Long> {
}
