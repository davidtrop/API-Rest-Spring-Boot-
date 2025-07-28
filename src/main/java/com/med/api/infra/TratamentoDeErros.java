package com.med.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors().stream().map(DadosErrosValidacao::new).toList();
        return  ResponseEntity.badRequest().body(erros);
    }

    private record DadosErrosValidacao(String campo, String mensagem){
        public DadosErrosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
