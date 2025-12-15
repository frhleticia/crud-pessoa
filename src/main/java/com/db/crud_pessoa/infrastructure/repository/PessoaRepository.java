package com.db.crud_pessoa.infrastructure.repository;

import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(Long cpf);

    @Transactional
    void deleteByCpf(Long cpf);
}
