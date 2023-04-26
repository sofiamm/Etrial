package com.Etrial.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "entrada")
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private Long idEntrada;
    private Long idEvento;
    private String descripcion;
    private Long idCliente;
    private double precio;
    private int existencias;
    private boolean activo;

    public Entrada() {
    }

    public Entrada(Long idEvento, String descripcion, Long idCliente, double precio, int existencias, boolean activo) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;
        this.idCliente = idCliente;
        this.precio = precio;
        this.existencias = existencias;
        this.activo = activo;
    }
}
