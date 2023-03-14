package com.bjlngroup.soscamp;

import com.bjlngroup.soscamp.repositories.ClientesRepository;
import com.bjlngroup.soscamp.repositories.PagamentosRepository;
import com.bjlngroup.soscamp.repositories.PlanosRepository;
import com.bjlngroup.soscamp.tables.Cliente;
import com.bjlngroup.soscamp.tables.Pagamento;
import com.bjlngroup.soscamp.tables.Plano;
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

    @Autowired
    private PlanosRepository planosRepository;

    @Autowired
    private PagamentosRepository pagamentosRepository;

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

    @GetMapping("/pagamentos")
    public Iterable<Pagamento> listarPagamentos() {
        return pagamentosRepository.findAll();
    }

    @GetMapping("/pagamentos/{id}")
    public Pagamento buscarPagamentoPorID(@PathVariable long id) {
        Optional<Pagamento> pagamentoOptional = pagamentosRepository.findById(id);

        if (pagamentoOptional.isPresent())
            return pagamentoOptional.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/planos")
    public Iterable<Plano> listarPlanos() {
        return planosRepository.findAll();
    }

    @GetMapping("/planos/{id}")
    public Plano buscarPlanoPorID(@PathVariable long id) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isPresent())
            return planoOptional.get();

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

    @PostMapping("/pagamentos/pay")
    public void inserirPagamento(@RequestBody Pagamento pagamento) {
        pagamentosRepository.save(pagamento);
    }

    @PostMapping("/planos/create")
    @ResponseStatus(HttpStatus.CREATED)
    private void inserirPlano(@RequestBody Plano plano) {
        planosRepository.save(plano);
    }

    // metodos PUT
    @PutMapping("/clientes/update/{id}")
    public void atualizarCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Cliente clienteEncontrado = clienteOptional.get();
        String senhaCriptografada = criptografarSenha(clienteEncontrado.getSenha());
        clienteEncontrado.setSenha(senhaCriptografada);

        clienteEncontrado.atualizar(cliente);
        clientesRepository.save(clienteEncontrado);
    }

    @PutMapping("/pagamentos/update/{id}")
    public void atualizarPagamento(@PathVariable long id, @RequestBody Pagamento pagamento) {
        Optional<Pagamento> pagamentoOptional = pagamentosRepository.findById(id);

        if (pagamentoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Pagamento pagamentoEncontrado = pagamentoOptional.get();
        pagamentoEncontrado.atualizar(pagamento);

        pagamentosRepository.save(pagamentoEncontrado);
    }

    @PutMapping("/planos/update/{id}")
    public void atualizarPlano(@PathVariable long id, Plano plano) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Plano planoEncontrado = planoOptional.get();
        planoEncontrado.atualizar(plano);

        planosRepository.save(planoEncontrado);
    }

    // metodos DELETE
    @DeleteMapping("clientes/delete/{id}")
    public void excluirCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clientesRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        clientesRepository.delete(clienteOptional.get());
    }

    @DeleteMapping("/pagamentos/delete/{id}")
    public void excluirPagamento(@PathVariable long id) {
        Optional<Pagamento> pagamentoOptional = pagamentosRepository.findById(id);

        if (pagamentoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        pagamentosRepository.delete(pagamentoOptional.get());
    }

    @DeleteMapping("/planos/delete/{id}")
    public void excluirPlano(@PathVariable long id) {
        Optional<Plano> planoOptional = planosRepository.findById(id);

        if (planoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        planosRepository.delete(planoOptional.get());
    }

    // metodos extras
    public String criptografarSenha(String str) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(str);
    }
}
