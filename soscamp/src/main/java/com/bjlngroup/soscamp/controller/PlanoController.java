package com.bjlngroup.soscamp.controller;

import com.bjlngroup.soscamp.models.Plano;
import com.bjlngroup.soscamp.repositories.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/planos")
public class PlanoController {
    @Autowired
    private PlanosRepository planosRepository;

    @GetMapping
    public Iterable<Plano> listarPlanos() {
        return planosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Plano buscarPlanoPorID(@PathVariable long id) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isPresent())
            return planoOptional.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    private ResponseEntity<Plano> inserirPlano(@RequestBody Plano plano) {
        return new ResponseEntity<>(planosRepository.save(plano), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public void atualizarPlano(@PathVariable long id, Plano plano) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Plano planoEncontrado = planoOptional.get();
        planoEncontrado.atualizar(plano);

        planosRepository.save(planoEncontrado);
    }

    @DeleteMapping("/delete/{id}")
    public void excluirPlano(@PathVariable long id) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        planosRepository.delete(planoOptional.get());
    }
}
