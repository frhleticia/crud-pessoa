package com.db.crud_pessoa.dto;

public record EnderecoDTO(Long id, String rua, Long numero, String bairro, String cidade, String estado, String cep) {
}
