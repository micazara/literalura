package com.alura.literalura.excepciones;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NoSePudoDeserializarException extends RuntimeException{

    public NoSePudoDeserializarException(String mensaje){
        super(mensaje);
    }
}
