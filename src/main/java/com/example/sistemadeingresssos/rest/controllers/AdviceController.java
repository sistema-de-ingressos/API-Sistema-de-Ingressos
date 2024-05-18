package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.exceptions.ClienteNaoEncontradoException;
import com.example.sistemadeingresssos.exceptions.EventoException;
import com.example.sistemadeingresssos.exceptions.IngressoNaoEncontradoException;
import com.example.sistemadeingresssos.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(EventoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleEventoException(EventoException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(IngressoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleIngressoNaoEncontradoException( IngressoNaoEncontradoException ex ){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(errors);
    }

}
