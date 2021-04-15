package com.maranata.domainmanagement.service;

import com.maranata.commonbean.management.entity.Associate;
import com.maranata.commonbean.management.entity.Child;
import com.maranata.commonbean.management.entity.Member;
import com.maranata.commonbean.management.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maranata.domainmanagement.dao.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ChildRepository childRepository;
    @Autowired
    AssociateRepository associateRepository;
    @Autowired
    AddressRepository addressRepository;



    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(long id){
        return personRepository.findByid(id);
    }

    public List<Person> findPersonByPn(String personalNumber) {
        return personRepository.findPersonBypersonalNumber(personalNumber);
    }

    /**
     * get all children and members from person using query repository method
     * @return
     */
    public List<Person> findAllMembersChildrenByQuery(){
        return  personRepository.findAllMembersChilds();
    }

    /**
     * get all members and children from person using entity repository
     * @return
     */
    public List<Person> findAllMembersChildrenByRepository(){
        return  personRepository.findAll().stream().filter(person-> memberRepository.existMemberBypersonalNumber(person.getPersonalNumber()))
                .filter(person-> childRepository.existChildByPersonalNumber(person.getPersonalNumber())).collect(Collectors.toList());
    }

    @Transactional
    public Person addPerson(Person person){
        addressRepository.save(person.getAddress());
        return personRepository.save(person);
    }

    @Transactional
    public Member addPersonMember(Member member){
      addPerson(member.getPerson());
      return memberRepository.save(member);
    }

    @Transactional
    public Child addPersonChild(Child child){
        addPerson(child.getPerson());
        return childRepository.save(child);
    }

    @Transactional
    public Associate addPersonAssociate(Associate associate){
        addPerson(associate.getPerson());
        return associateRepository.save(associate);
    }

    public ResponseEntity<Person> update(Person inPerson){
        try {
            Person newPerson = personRepository.findById(inPerson.getId()).orElseThrow(RuntimeException::new);
            newPerson.setSurname(inPerson.getSurname());
            newPerson.setName(inPerson.getName());
            newPerson.setPersonalNumber(inPerson.getPersonalNumber());
            newPerson.setGender(inPerson.getGender());
            newPerson.setDayOfBirth(inPerson.getDayOfBirth());
            newPerson.setAddress(inPerson.getAddress());
            personRepository.save(newPerson);
            return ResponseEntity.ok(newPerson);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void delete(Long idPersona){
        Person person = personRepository.findById(idPersona).get();
        addressRepository.deleteById(person.getAddress().getId());
        personRepository.deleteById(idPersona);
    }
}
