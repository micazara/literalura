package com.alura.literalura.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
