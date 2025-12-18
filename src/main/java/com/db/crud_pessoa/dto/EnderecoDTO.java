package com.db.crud_pessoa.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(Long id, Boolean principal, String rua, Long numero, String bairro, String cidade, String estado, String cep) {
}