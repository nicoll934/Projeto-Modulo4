package com.bjlngroup.soscamp;

import com.bjlngroup.soscamp.repositories.ClientesRepository;
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
    private ClientesRepository clientesRepository;

    // metodos GET

    // - para remover depois
    @GetMapping("/clientes")
    public Iterable<Cliente> listarTodos() {
        return clientesRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Cliente pesquisarPorID(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isPresent())
            return clienteOptional.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    // - fim


    @GetMapping("/clientes/login/{email}/{senha}")
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

    // metodos POST
    @PostMapping("/clientes/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public void inserirCliente(@RequestBody Cliente cliente) {
        // Criptografa a senha antes de colocar o usuario no banco
        String senhaCriptografada = criptografarSenha(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);
        clientesRepository.save(cliente);
    }

    // metodos PUT
    @PutMapping("clientes/update/{id}")
    public void atualizarCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Cliente clienteEncontrado = clienteOptional.get();
        String senhaCriptografada = criptografarSenha(clienteEncontrado.getSenha());
        clienteEncontrado.setSenha(senhaCriptografada);

        clientesRepository.save(clienteEncontrado);
    }

    // metodos DELETE
    @DeleteMapping("clientes/delete/{id}")
    public void excluirCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        clientesRepository.delete(clienteOptional.get());
    }

    // metodos extras
    public String criptografarSenha(String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(str);
    }
}
