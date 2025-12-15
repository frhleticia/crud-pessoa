package com.db.crud_pessoa.infrastructure.entitys;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Integer id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @NotNull
    @Column(name = "cpf", unique = true, nullable = false)
    private Long cpf;
}
