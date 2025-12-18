package com.db.crud_pessoa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(Long id, Boolean principal,
                          @NotBlank String rua,
                          @NotNull Long numero,
                          @NotBlank String bairro,
                          @NotBlank String cidade,
                          @NotBlank String estado,
                          @NotBlank String cep) {
}