package io.reflectoring.reto.controller;

import io.reflectoring.reto.entity.Orden;
import io.reflectoring.reto.repository.OrdenRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin("http://localhost:4200/")
@Tag(name = "Órdenes", description = "Operaciones relacionadas con órdenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping
    public List<Orden> obtenerOrdenes() {
        return ordenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long id) {
        return ordenRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Orden crearOrden(@RequestBody Orden orden) {
        return ordenRepository.save(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long id, @RequestBody Orden ordenActualizada) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    orden.setCantidad(ordenActualizada.getCantidad());
                    orden.setTotal(ordenActualizada.getTotal());
                    orden.setFechaOrden(ordenActualizada.getFechaOrden());
                    orden.setProducto(ordenActualizada.getProducto());
                    orden.setCliente(ordenActualizada.getCliente());
                    return ResponseEntity.ok(ordenRepository.save(orden));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarOrden(@PathVariable Long id) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    ordenRepository.delete(orden);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
