package com.db.crud_pessoa.mapper;

import com.db.crud_pessoa.dto.EnderecoDTO;
import com.db.crud_pessoa.dto.PessoaRequest;
import com.db.crud_pessoa.dto.PessoaResponse;
import com.db.crud_pessoa.infrastructure.entitys.Endereco;
import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaMapper {

    //para transformar os dados entregues, em uma entidade prórpria
    public Pessoa toEntity(PessoaRequest req){
        Pessoa p = new Pessoa();
        p.setNome(req.nome());
        p.setCpf(req.cpf());
        p.setDataNascimento(req.dataNascimento());
        List<Endereco> enderecos = req.enderecos().stream().map(e -> {
            Endereco endereco = new Endereco();
            endereco.setRua(e.rua());
            endereco.setBairro(e.bairro());
            endereco.setCep(e.cep());
            endereco.setCidade(e.cidade());
            endereco.setNumero(e.numero());
            endereco.setEstado(e.estado());
            endereco.setPessoa(p);
            return endereco;
        }).toList();
        p.setEnderecos(enderecos);
        return p;
    }

    public Endereco toEnderecoEntity(EnderecoDTO dto, Pessoa pessoa){
        Endereco e = new Endereco();
        e.setRua(dto.rua());
        e.setNumero(dto.numero());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        e.setCep(dto.cep());
        e.setPessoa(pessoa);
        return e;
    }

    public PessoaResponse toResponse(Pessoa p){
        List<EnderecoDTO> enderecos = p.getEnderecos().stream().map(e -> new EnderecoDTO(e.getId(), e.getRua(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado(), e.getCep())).toList();
        return new PessoaResponse(p.getId(), p.getNome(), p.getDataNascimento(), p.getCpf(), enderecos);
    }
}