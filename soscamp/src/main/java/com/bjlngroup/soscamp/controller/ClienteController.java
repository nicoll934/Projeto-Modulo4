package com.bjlngroup.soscamp.controller;

import com.bjlngroup.soscamp.models.Cliente;
import com.bjlngroup.soscamp.repositories.ClientesRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClientesRepository clientesRepository;

    // - para remover depois
    @GetMapping("/list-all")
    public Iterable<Cliente> listarTodos() {
        return clientesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente pesquisarPorID(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isPresent())
            return clienteOptional.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    // - fim

    @GetMapping("/login/{email}/{senha}")
    public Cliente login(@PathVariable String email, @PathVariable String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for (Cliente cliente : clientesRepository.findAll()) {
            if (cliente.getEmail().equalsIgnoreCase(email) && encoder.matches(senha, cliente.getSenha()))
                return cliente;
            else if (cliente.getEmail().equalsIgnoreCase(email) &&
                    !encoder.matches(senha, cliente.getSenha()))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signin")
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        if (!cliente.getSenha().isEmpty()) {
            // Criptografa a senha antes de colocar o usuario no banco
            String senhaCriptografada = criptografarSenha(cliente.getSenha());
            cliente.setSenha(senhaCriptografada);

            return new ResponseEntity<>(clientesRepository.save(cliente), HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public void atualizarCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Cliente clienteEncontrado = clienteOptional.get();
        if (!clienteEncontrado.getNome().equalsIgnoreCase(cliente.getNome()))
            clienteEncontrado.setNome(cliente.getNome());

        if (!clienteEncontrado.getSenha().equalsIgnoreCase(cliente.getSenha()))
            clienteEncontrado.setSenha(criptografarSenha(clienteEncontrado.getSenha()));

        if (!clienteEncontrado.getTelefone().equalsIgnoreCase(cliente.getTelefone()))
            clienteEncontrado.setTelefone(cliente.getTelefone());

        if (!clienteEncontrado.getEndereco().equalsIgnoreCase(cliente.getEndereco()))
            clienteEncontrado.setEndereco(cliente.getEndereco());

        if (!clienteEncontrado.getAlergias().equalsIgnoreCase(cliente.getAlergias()))
            clienteEncontrado.setAlergias(cliente.getAlergias());

        clientesRepository.save(clienteEncontrado);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        clientesRepository.delete(clienteOptional.get());
    }

    public String criptografarSenha(String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(str);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> tratar(ConstraintViolationException exception) {
        List<String> erros = new ArrayList<>();

        for(ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String erro = String.format(
                    "%s %s",
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );

            erros.add(erro);
        }

        return erros;
    }
}
