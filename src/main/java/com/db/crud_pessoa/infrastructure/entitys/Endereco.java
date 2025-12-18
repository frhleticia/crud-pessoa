package com.db.crud_pessoa.infrastructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean principal;
    private String rua;
    private Long numero;
    private String bairro;
    private String cidade;
    private String estado;
    @Size(min = 8, max = 8, message = "Um CEP válido tem 8 dígitos.")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}