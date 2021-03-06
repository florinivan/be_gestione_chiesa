package com.maranata.domainmanagement.dao;

import com.maranata.commonbean.management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT COUNT(m)>0 FROM Member " +
            "m WHERE m.person.personalNumber =:personalNumber ")
    Boolean existMemberBypersonalNumber(String personalNumber);

}



