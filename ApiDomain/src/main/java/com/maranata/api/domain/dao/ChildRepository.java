package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChildRepository extends JpaRepository<Child,Long> {

    @Query("SELECT COUNT(b)>0 FROM Child c WHERE c.person.personalNumber= :personalNumber")
    boolean existChildByPersonalNumber(String personalNumber);

    @Query("SELECT c FROM Child c WHERE c.fatherPersonalNumber = :fatherPersonalNumber")
    Child findChildByFather(String fatherPersonalNumber);

    @Query("SELECT c FROM Child c WHERE c.matherPersonalNumber = :matherPersonalNumber")
    Child findChildByMather (String matherPersonalNumber);

}
