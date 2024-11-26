package com.alura.literalura.servicio;

import com.alura.literalura.excepciones.NoSePudoDeserializarException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deserializador implements IDeserializador {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> objeto) {
        try {
            return objectMapper.readValue(json, objeto);
        } catch (JsonProcessingException e) {
            throw new NoSePudoDeserializarException("Ocurrio un error al intentar deserializar la respuesta");
        }
    }

}
