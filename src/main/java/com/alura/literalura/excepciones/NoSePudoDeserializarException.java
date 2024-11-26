package com.alura.literalura.excepciones;

public class NoSePudoDeserializarException extends RuntimeException{

    public NoSePudoDeserializarException(String mensaje){
        super(mensaje);
    }
}
