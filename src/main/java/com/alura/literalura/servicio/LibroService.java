package com.alura.literalura.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    public Libro save(Libro libro) {
        return this.repository.save(libro);
    }

    public List<Libro> obtenerTodosLosLibros() {
      return this.repository.findAllByOrderByTituloAsc();
    }

}
