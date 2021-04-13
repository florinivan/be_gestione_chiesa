package com.maranata.domainmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maranata.domainmanagement.entity.Presence;

public interface PresenceRepository extends JpaRepository<Presence,String> {
}
