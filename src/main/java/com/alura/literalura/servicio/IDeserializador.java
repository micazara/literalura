package com.alura.literalura.servicio;

public interface IDeserializador {

    <T> T obtenerDatos(String json, Class<T> objeto);

}
