package com.alura.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
                @JsonAlias("title") String titulo,
                @JsonAlias("authors") List<DatosAutor> autores,
                @JsonAlias("languages") List<String> idiomas,
                @JsonAlias("download_count") Integer cantidadDescargas) {

        public String obtenerPrimerIdioma() {
                return (idiomas != null && !idiomas.isEmpty()) ? idiomas.get(0) : null;
        }

        public DatosAutor obtenerPrimerAutor() {
                return (autores != null && !autores.isEmpty()) ? autores.get(0) : null;
        }

}
