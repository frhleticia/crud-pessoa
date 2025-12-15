package com.db.crud_pessoa.business;

import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import com.db.crud_pessoa.infrastructure.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository){
        this.repository = repository;
    }

    public void salvarPessoa(Pessoa pessoa){
        repository.saveAndFlush(pessoa);
    }

    public Pessoa buscarPessoaPorCpf(Long cpf){
        return repository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("CPF não encontrado.")
        );
    }

    public void deletarPessoaPorCpf(Long cpf){
        repository.deleteByCpf(cpf);
    }

    public void atualizarPessoaPorId(Integer id, Pessoa pessoa){
        Pessoa pessoaEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado."));
        Pessoa usuarioAtualizado = Pessoa.builder()
                .nome(pessoa.getNome() != null ?
                        pessoa.getNome() : pessoaEntity.getNome())
                .dataNascimento(pessoa.getDataNascimento() != null ?
                        pessoa.getDataNascimento() : pessoaEntity.getDataNascimento())
                .cpf(pessoa.getCpf() != null ?
                        pessoa.getCpf() : pessoaEntity.getCpf())
                .id(pessoaEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}