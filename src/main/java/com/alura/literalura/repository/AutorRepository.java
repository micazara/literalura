package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.alura.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    Optional<Autor> findByNombre(String nombre);

    List<Autor> findAllByOrderByNombreAsc();

}
