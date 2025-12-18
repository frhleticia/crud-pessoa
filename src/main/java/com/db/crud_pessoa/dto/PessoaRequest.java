package com.db.crud_pessoa.dto;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequest(Long id, String nome, LocalDate dataNascimento, String cpf, List<EnderecoDTO> enderecos) {
}
