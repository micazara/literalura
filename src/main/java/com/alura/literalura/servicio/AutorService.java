package com.alura.literalura.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Optional<Autor> findByNombre(String nombre) {
        return this.repository.findByNombre(nombre);
    }

    public Autor save(Autor autor) {
        return this.repository.save(autor);
    }

    public List<Autor> obtenerTodosLosAutores() {
        return this.repository.findAllByOrderByNombreAsc();
    }

    public List<Autor> obtenerAutoresVivosEnUnDeterminadoAnio(int anio) {
       return this.repository.obtenerAutoresVivosEnUnDeterminadoAnio(anio);
    }
}
