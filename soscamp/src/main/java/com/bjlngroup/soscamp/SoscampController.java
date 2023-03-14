package com.bjlngroup.soscamp;

import com.bjlngroup.soscamp.repositories.ClienteRepository;
import com.bjlngroup.soscamp.tables.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class SoscampController {
    // Repositorios
    @Autowired
    private ClienteRepository repository;

    // metodos GET
    @GetMapping("/clientes")
    public Iterable<Cliente> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Cliente pesquisarPorID(@PathVariable long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);

        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // metodos POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserirCliente(@RequestBody Cliente cliente) {
        // Criptografa a senha antes de a colocar no banco
        String senhaCriptografada = criptografarSenha(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);

        repository.save(cliente);
    }

    // metodos PUT
    @PutMapping("/{id}")
    public void atualizarCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Cliente clienteEncontrado = clienteOptional.get();

        String senhaCriptografada = criptografarSenha(clienteEncontrado.getSenha());
        clienteEncontrado.setSenha(senhaCriptografada);

        clienteEncontrado.atualizar(clienteEncontrado);
        repository.save(clienteEncontrado);
    }

    // metodos DELETE
    @DeleteMapping("clientes/{id}")
    public void excluirCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional =repository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.delete(clienteOptional.get());
    }

    // metodos extra
    public String criptografarSenha(String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(str);
    }
}
