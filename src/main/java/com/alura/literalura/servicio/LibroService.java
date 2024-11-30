package com.alura.literalura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    public void save(Libro libro) {
        this.repository.save(libro);
    }

}
