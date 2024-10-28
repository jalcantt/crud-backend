package io.reflectoring.reto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    private int cantidad;

    @NotNull
    private BigDecimal total;

    @NotNull
    private LocalDate fechaOrden;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @NotNull
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @NotNull
    private Cliente cliente;

}
