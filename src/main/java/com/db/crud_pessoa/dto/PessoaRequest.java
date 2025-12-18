package com.db.crud_pessoa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequest(Long id, @NotBlank String nome, LocalDate dataNascimento, @NotBlank @CPF String cpf, @Valid List<EnderecoDTO> enderecos) {
}