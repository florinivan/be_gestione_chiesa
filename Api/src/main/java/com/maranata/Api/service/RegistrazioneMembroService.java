package com.maranata.Api.service;

import com.maranata.Api.dto.*;
import com.maranata.Api.utils.MembroExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class RegistrazioneMembroService {

    @Autowired
    MembroService membroService;
    @Autowired
    PersonaService personaService;
    @Autowired
    BambinoService bambinoService;


    public RegistrazioneMembroDto registrazioneMembro(RegistrazioneMembroDto registrazioneMembroDto)  {

        if (registrazioneMembroDto != null) {
            MembroDto membroDto=registrazioneMembroDto.getMembroDto();
            PersonaDto personaDto=registrazioneMembroDto.getMembroDto().getPersona();
            ConiugeDto coniugeDto=registrazioneMembroDto.getConiugeDto();
            if(membroService.membroCheck(membroDto.getPersona().getCodiceFiscale())){
                throw new  MembroExistException(String.format("membro with cf %s already exists", membroDto.getPersona().getCodiceFiscale()));
            }else{
                Collection<PersonaDto> personaListDto = personaService.findBycodiceFiscale(membroDto.getPersona().getCodiceFiscale()).getBody();
                if(personaListDto!=null && !personaListDto.isEmpty()){
                    PersonaDto personaDtoByCF=personaListDto.stream().findAny().get();
                    personaService.personaUpdate(personaDto,personaDtoByCF.getId());
                    registrazioneMembroDto.setMembroDto(membroService.membroAdd(membroDto));
                }else{
                    registrazioneMembroDto.setMembroDto(membroService.membroPersonaAdd(membroDto));
                }
                registrazioneMembroDto.getBambini().forEach(bambinoDto->{
                    bambinoService.bambinoAdd(bambinoDto);
                });
                personaService.coniugeAdd(coniugeDto);
            }
        }
        return registrazioneMembroDto;
    }
}
