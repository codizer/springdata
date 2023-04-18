package com.company.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class SpringdataApplicationTests {

	@Autowired
	LibroRepository repositoryLibro;

	@Test
	void buscarTodosTest() {
		Iterable<Libro> it = repositoryLibro.findAll();
		List<Libro> miLista = new ArrayList<Libro>();
		// it.forEach(System.out::println);
		it.forEach(miLista::add);

		assertThat(miLista.size(), greaterThan(6));
	}

}
