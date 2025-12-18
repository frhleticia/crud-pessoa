package com.db.crud_pessoa.controller;

import com.db.crud_pessoa.business.PessoaService;
import com.db.crud_pessoa.dto.EnderecoDTO;
import com.db.crud_pessoa.dto.PessoaRequest;
import com.db.crud_pessoa.dto.PessoaResponse;
import com.db.crud_pessoa.infrastructure.entitys.Endereco;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public List<PessoaResponse> listarTodos(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}/enderecos")
    public List<EnderecoDTO> listarEnderecos(@PathVariable Long id){
        return pessoaService.listarEnderecos(id);
    }

    @GetMapping("/{pessoaId}/enderecos/principais")
    public List<EnderecoDTO> listarEnderecosPrincipais(@PathVariable Long pessoaId) {
        return pessoaService.listarEnderecosPrincipais(pessoaId);
    }

    @GetMapping("/{id}/idade")
    public Integer mostrarIdade(@PathVariable Long id){
        return pessoaService.calcularIdade(id);
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@Valid @RequestBody PessoaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(request));
    }

    @PostMapping("/{id}/enderecos")
    public PessoaResponse addEnderecoPorId(@PathVariable Long id, @Valid @RequestBody EnderecoDTO endereco){
        return pessoaService.adicionarEnderecoPorId(id, endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequest request){
        return ResponseEntity.ok(pessoaService.atualizarTudo(id, request));
    }

    @PutMapping("/{pessoaId}/enderecos/{enderecoId}")
    public PessoaResponse atualizarEnderecoPorId(@PathVariable Long pessoaId, @PathVariable Long enderecoId, @Valid @RequestBody EnderecoDTO endereco){

        return pessoaService.atualizarEnderecoPorId(pessoaId, enderecoId, endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        pessoaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
