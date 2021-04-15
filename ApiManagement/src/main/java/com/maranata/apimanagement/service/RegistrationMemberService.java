package com.maranata.apimanagement.service;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.management.dto.RegistrationMemberDto;
import com.maranata.commonbean.management.entity.*;
import com.maranata.apimanagement.utils.ExistMemberException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;


@Service
public class RegistrationMemberService {

    private static ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MemberService memberService;
    @Autowired
    PersonService personService;
    @Autowired
    ChildService childService;


    public RegistrationMemberDto memberRegistration(RegistrationMemberDto registrationMemberDto)  {

        if (registrationMemberDto != null) {
            Member member = registrationMemberDto.getMember();
            Person person = registrationMemberDto.getMember().getPerson();
            Spouse dtoSpouseDto = registrationMemberDto.getSpouse();
            if(memberService.memberCheck(member.getPerson().getPersonalNumber())){
                throw new ExistMemberException(String.format("membro with cf %s already exists", member.getPerson().getPersonalNumber()));
            }else{
                Collection<Person> personaListDto = personService.findBypersonalNumber(member.getPerson().getPersonalNumber()).getBody();
                if(personaListDto!=null && !personaListDto.isEmpty()){
                    Person personDtoByCF =personaListDto.stream().findAny().get();
                    personService.personUpdate(person, personDtoByCF.getId());
                    registrationMemberDto.setMember(memberService.memberAdd(member));
                }else{
                    registrationMemberDto.setMember(memberService.memberPersonAdd(member));
                }
                registrationMemberDto.getChildren().forEach(bambinoDto->{
                    childService.childAdd(bambinoDto);
                });
                personService.spouseAdd(dtoSpouseDto);
            }
        }
        return registrationMemberDto;
    }

    public static <T> T applyPatch(T originalObj, JsonPatch patch)
            throws  IOException, JsonPatchException {
        // Convert the original object to JsonNode
        JsonNode originalObjNode = objectMapper.valueToTree(originalObj);
        // Apply the patch
        TreeNode patchedObjNode = patch.apply(originalObjNode);
        // Convert the patched node to an updated obj
        return objectMapper.treeToValue(patchedObjNode, (Class<T>) originalObj.getClass());
    }

}
