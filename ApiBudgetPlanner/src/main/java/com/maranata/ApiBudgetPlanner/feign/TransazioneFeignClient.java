package com.maranata.ApiBudgetPlanner.feign;

import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.TipoTransazione;
import com.maranata.budgetplanner.entity.Transazione;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(value = "api-bugets", url = "http://localhost:8084/v1/transazioni/")
public interface TransazioneFeignClient {

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Transazione>> transazioniList();

    @GetMapping("/findBy/{tipo}")
    @Headers("Content-Type: application/json")
    List<Transazione> getTransactionsByTipoAndDateBetween(@PathVariable TipoTransazione tipo,
                                                          @RequestParam Long flussoId,
                                                          @RequestParam Long startDate,
                                                          @RequestParam Long endDate);


    @GetMapping("/{id}")
    ResponseEntity<Optional<Transazione>> checkById(@PathVariable long id);

    @PostMapping("/{tipo}")
    Budget addTransazioni(@RequestBody Transazione transazione);

    @PatchMapping(path = "/{id}" )
    Budget updateTransazioni(@RequestBody Transazione transazione,@PathVariable long id);
}
