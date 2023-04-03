package com.bjlngroup.soscamp.controller;

import com.bjlngroup.soscamp.models.Cliente;
import com.bjlngroup.soscamp.models.Pagamento;
import com.bjlngroup.soscamp.request.PagamentoRequest;
import com.bjlngroup.soscamp.models.Plano;
import com.bjlngroup.soscamp.repositories.ClientesRepository;
import com.bjlngroup.soscamp.repositories.PagamentosRepository;
import com.bjlngroup.soscamp.repositories.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private PlanosRepository planosRepository;

    @Autowired
    private PagamentosRepository pagamentosRepository;

    @GetMapping
    public Iterable<Pagamento> listarPagamentos() {
        return pagamentosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pagamento buscarPagamentoPorID(@PathVariable long id) {
        Optional<Pagamento> pagamentoOptional = pagamentosRepository.findById(id);

        if (pagamentoOptional.isPresent())
            return pagamentoOptional.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pay")
    public ResponseEntity<Pagamento> inserirPagamento(@RequestBody PagamentoRequest pagamentoRequest) {
        Optional<Cliente> cliente = clientesRepository.findById(pagamentoRequest.getCliente_id());
        Optional<Plano> plano = planosRepository.findById(pagamentoRequest.getPlano_id());

        if (cliente.isPresent() && plano.isPresent()) {
            Pagamento pagamento = new Pagamento();
            pagamento.setCliente(cliente.get());
            pagamento.setPlano(plano.get());
            pagamento.setData(LocalDate.now().toString());

            return new ResponseEntity<>(pagamentosRepository.save(pagamento), HttpStatus.CREATED);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public void excluirPagamento(@PathVariable long id) {
        Optional<Pagamento> pagamentoOptional = pagamentosRepository.findById(id);

        if (pagamentoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        pagamentosRepository.delete(pagamentoOptional.get());
    }
}
