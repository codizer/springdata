package com.company.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

	@Test
	void buscarUnoTest() {
		Optional<Libro> optionalLibro = repositoryLibro.findById("1A");

		if (optionalLibro.isPresent()) {
			assertThat(optionalLibro.get().getTitulo(), equalTo("Java"));
		}
	}

	@Test
	void insertarUnoTest() {
		Libro libro = new Libro("1F", "Python", "Alejandro", new Date(), 25.0);
		repositoryLibro.save(libro);

		Optional<Libro> optionalLibro = repositoryLibro.findById("1F");
		if (optionalLibro.isPresent()) {
			assertThat(optionalLibro.get().getTitulo(), equalTo("Python"));
		}
	}

	@Test
	void borrarUnoTest() {
		Libro libro = new Libro("1A");
		repositoryLibro.delete(libro);
		Optional<Libro> optionalLibro = repositoryLibro.findById("1A");
		// assertFalse(optionalLibro.isPresent());
		assertThat(optionalLibro.isPresent(), equalTo(false));
	}

}
