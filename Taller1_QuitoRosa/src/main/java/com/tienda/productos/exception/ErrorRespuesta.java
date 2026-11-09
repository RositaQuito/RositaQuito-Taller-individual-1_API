package com.tienda.productos.exception;

import java.time.LocalDateTime;

public class ErrorRespuesta {

    private final int estado;
    private final String error;
    private final String mensaje;
    private final LocalDateTime fecha;

    public ErrorRespuesta(int estado, String error, String mensaje) {
        this.estado = estado;
        this.error = error;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    public int getEstado() {
        return estado;
    }

    public String getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
