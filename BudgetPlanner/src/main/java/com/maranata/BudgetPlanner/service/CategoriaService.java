package com.maranata.budgetplanner.service;

import com.maranata.budgetplanner.dao.CategoriaRepository;

import com.maranata.budgetplanner.entity.Categoria;
import com.maranata.budgetplanner.entity.TipoCategoria;
import com.maranata.budgetplanner.entity.Transazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> findByDescrizione(String descrizione){
        return categoriaRepository.findByDescrizione(descrizione);
    }

    public Categoria add (Categoria categoria,TipoCategoria tipoCategoria){
        categoria.setTipoCategoria(tipoCategoria);
        return categoriaRepository.save(categoria);
    }

    public ResponseEntity<Categoria> update(Categoria inCategoria){
        try{
            Categoria newCategoria = categoriaRepository.findById(inCategoria.getId()).orElseThrow(RuntimeException::new);
            newCategoria.setDescrizione(inCategoria.getDescrizione());
            categoriaRepository.save(newCategoria);
            return ResponseEntity.ok(newCategoria);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void deletebyId(Long id){
        categoriaRepository.deleteById(id);

    }
}
