package com.alura.literalura.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    List<Libro> findAllByOrderByTituloAsc();

}
