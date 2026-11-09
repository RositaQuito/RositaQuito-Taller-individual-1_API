package com.tienda.productos.service;

import com.tienda.productos.exception.ProductoInvalidoException;
import com.tienda.productos.exception.ProductoNoEncontradoException;
import com.tienda.productos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    private final List<Producto> productos = new CopyOnWriteArrayList<>();

    private final AtomicLong generadorId = new AtomicLong();

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    public Producto obtenerPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    public Producto crear(Producto producto) {
        validar(producto);
        producto.setId(generadorId.incrementAndGet());
        productos.add(producto);
        return producto;
    }

    public void eliminar(Long id) {
        if (!productos.removeIf(producto -> producto.getId().equals(id))) {
            throw new ProductoNoEncontradoException(id);
        }
    }

    private void validar(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoInvalidoException("El nombre del producto es obligatorio");
        }
        Double precio = producto.getPrecio();
        if (precio == null || !Double.isFinite(precio) || precio <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser un valor negativo");
        }
    }
}
