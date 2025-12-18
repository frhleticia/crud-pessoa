package com.db.crud_pessoa.dto;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public record PessoaResponse(Long id, String nome, LocalDate dataNascimento, String cpf, List<EnderecoDTO> enderecos) {
}
