package com.db.crud_pessoa.infrastructure.entitys;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pessoa")
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;
}