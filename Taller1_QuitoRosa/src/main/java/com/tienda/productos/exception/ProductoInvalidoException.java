package com.tienda.productos.exception;

public class ProductoInvalidoException extends RuntimeException {

    public ProductoInvalidoException(String motivo) {
        super(motivo);
    }
}
