package com.db.crud_pessoa.controller;

import com.db.crud_pessoa.business.PessoaService;
import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping //guardar dados
    public ResponseEntity<Void> salvarPessoa(@RequestBody Pessoa pessoa){
        pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.ok().build(); //qualquer resposta ta ok
    }

    @GetMapping
    public ResponseEntity<Pessoa> buscarPessoaPorCpf(@RequestBody Long cpf){
        return ResponseEntity.ok(pessoaService.buscarPessoaPorCpf(cpf));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPessoaPorCpf(@RequestBody Long cpf){
        pessoaService.deletarPessoaPorCpf(cpf);
        return ResponseEntity.ok().build();
    }

    @PutMapping //atualiza tudo
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestBody Integer id,
                                                      @RequestBody Pessoa pessoa){
        pessoaService.atualizarPessoaPorId(id, pessoa);
        return ResponseEntity.ok().build();
    }
}
