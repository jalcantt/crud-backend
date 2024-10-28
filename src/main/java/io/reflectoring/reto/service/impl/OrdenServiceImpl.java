package io.reflectoring.reto.service.impl;

import io.reflectoring.reto.entity.Orden;
import io.reflectoring.reto.entity.Producto;
import io.reflectoring.reto.repository.OrdenRepository;
import io.reflectoring.reto.repository.ProductoRepository;
import io.reflectoring.reto.service.OrdenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class OrdenServiceImpl implements OrdenService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    public Orden crearOrden(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));

        Orden orden = new Orden();
        orden.setCantidad(cantidad);
        orden.setTotal(total);
        orden.setFechaOrden(LocalDate.now());
        orden.setProducto(producto);

        return ordenRepository.save(orden);
    }
}
