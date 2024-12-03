package com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findAllByOrderByTituloAsc();

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findAllByIdioma(String idioma);

}
