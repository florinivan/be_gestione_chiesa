package com.maranata.domainmanagement.dao;

import com.maranata.commonbean.management.entity.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence,String> {
}
