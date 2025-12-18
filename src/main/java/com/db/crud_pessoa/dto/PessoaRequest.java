package com.db.crud_pessoa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequest(Long id, @NotBlank String nome, LocalDate dataNascimento, @NotBlank @CPF String cpf, @NotNull @Size(min = 1) @Valid List<EnderecoDTO> enderecos) {
}