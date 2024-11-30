package com.alura.literalura.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "Libros")
public class Libro {
    private String titulo;
    @ManyToMany
    @JoinTable(name = "libro_autor", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id"), // Columna de la tabla "libro"
            inverseJoinColumns = @JoinColumn(name = "autor_id") // Columna de la tabla "autor"
    )
    private List<Autor> autores;
    private String idioma;
    private Integer cantidadDescargas;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Libro() {

    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = new ArrayList<>();
        this.idioma = datosLibro.obtenerPrimerIdioma();
        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        id = Id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}
