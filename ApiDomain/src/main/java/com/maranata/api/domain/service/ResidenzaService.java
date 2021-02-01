package com.maranata.api.domain.service;

import com.maranata.api.domain.controller.ResidenzaController;
import com.maranata.api.domain.dao.ResidenzaRepository;
import com.maranata.api.domain.entity.Residenza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenzaService {

    @Autowired
    ResidenzaRepository residenzaRepository;

    public Residenza add(Residenza residenza)  {
     return residenzaRepository.save(residenza);
    }

}
