package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    List<Autor> findAllByOrderByNombreAsc();

    // @Query(value = "SELECT * FROM autores a WHERE a.fecha_nacimiento <= :anio AND
    // a.fecha_fallecimiento < :anio", nativeQuery = true)
    @Query(value = "SELECT * FROM autores a WHERE CAST(a.fecha_nacimiento AS INTEGER) <= :anio AND CAST(a.fecha_fallecimiento AS INTEGER) > :anio", nativeQuery = true)
    List<Autor> obtenerAutoresVivosEnUnDeterminadoAnio(@Param("anio") int anio);

}
