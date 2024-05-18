package com.example.sistemadeingresssos.exceptions;

public class EventoException extends RuntimeException{
    public EventoException() {
        super("Evento não encontrado.");
    }
}
