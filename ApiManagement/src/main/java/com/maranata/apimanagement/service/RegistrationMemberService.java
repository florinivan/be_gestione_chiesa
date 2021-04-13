package com.maranata.apimanagement.service;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.apimanagement.dto.*;
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
            MemberDto memberDto = registrationMemberDto.getMemberDto();
            PersonDto personDto = registrationMemberDto.getMemberDto().getPerson();
            SpouseDto spouseDto = registrationMemberDto.getSpouseDto();
            if(memberService.memberCheck(memberDto.getPerson().getPersonalNumber())){
                throw new ExistMemberException(String.format("membro with cf %s already exists", memberDto.getPerson().getPersonalNumber()));
            }else{
                Collection<PersonDto> personaListDto = personService.findBypersonalNumber(memberDto.getPerson().getPersonalNumber()).getBody();
                if(personaListDto!=null && !personaListDto.isEmpty()){
                    PersonDto personDtoByCF =personaListDto.stream().findAny().get();
                    personService.personUpdate(personDto, personDtoByCF.getId());
                    registrationMemberDto.setMemberDto(memberService.memberAdd(memberDto));
                }else{
                    registrationMemberDto.setMemberDto(memberService.memberPersonAdd(memberDto));
                }
                registrationMemberDto.getChildren().forEach(bambinoDto->{
                    childService.childAdd(bambinoDto);
                });
                personService.spouseAdd(spouseDto);
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
