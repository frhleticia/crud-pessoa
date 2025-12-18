package com.db.crud_pessoa;

import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

class CrudPessoaApplicationTests {

	@Test
	void testeCreatePessoaSuccess() {
        Pessoa pessoa = Pessoa.builder()
                .nome("Letícia")
                .cpf("12345678901")
                .dataNascimento(LocalDate.of(2001, 12, 30))
                .build();
        //validar se foi salvo certo
        assertNotNull(pessoa);
        assertEquals("Letícia", pessoa.getNome());
        assertEquals("12345678901", pessoa.getCpf());
        assertEquals(LocalDate.of(2001, 12, 30), pessoa.getDataNascimento());
    }

    @Test
    void testeCreatePessoaFail() {

    }

}
