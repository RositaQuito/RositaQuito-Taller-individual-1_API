package com.tienda.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<ErrorRespuesta> productoNoEncontrado(ProductoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<ErrorRespuesta> productoInvalido(ProductoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorRespuesta> jsonInvalido(HttpMessageNotReadableException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no tiene un formato JSON válido");
    }

    private ResponseEntity<ErrorRespuesta> construirRespuesta(HttpStatus estado, String mensaje) {
        ErrorRespuesta cuerpo = new ErrorRespuesta(estado.value(), estado.getReasonPhrase(), mensaje);
        return new ResponseEntity<>(cuerpo, estado);
    }
}
