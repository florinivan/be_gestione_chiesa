package com.maranata.domainmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maranata.domainmanagement.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findPersonBypersonalNumber(String personalNumber);

    @Query("SELECT p FROM Person p WHERE EXISTS ( SELECT m FROM Member m WHERE p.id = m.person.id ) OR EXISTS (SELECT c FROM Child c WHERE p.id = c.person.id)")
    List<Person> findAllMembersChilds();

    public Optional<Person> findByid(Long id);

}
