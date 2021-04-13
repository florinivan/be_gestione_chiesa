package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence,String> {
}
