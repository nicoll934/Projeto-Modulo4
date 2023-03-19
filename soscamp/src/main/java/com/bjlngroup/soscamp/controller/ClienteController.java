package com.bjlngroup.soscamp.controller;

import com.bjlngroup.soscamp.models.Cliente;
import com.bjlngroup.soscamp.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClientesRepository clientesRepository;

    // - para remover depois
    @GetMapping
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
        // Criptografa a senha antes de colocar o usuario no banco
        String senhaCriptografada = criptografarSenha(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);

        return new ResponseEntity<>(clientesRepository.save(cliente), HttpStatus.CREATED);
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

    @DeleteMapping("/delete/{id}")
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
}
