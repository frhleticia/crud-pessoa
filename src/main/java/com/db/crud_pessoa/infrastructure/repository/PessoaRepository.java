package com.db.crud_pessoa.infrastructure.repository;

import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
