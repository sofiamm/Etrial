package com.Etrial.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;
    private String nombre;
    private String lugar;
    private String descripcion;
    private boolean activo;
    @Column(name = "ruta_imagen")
    private String rutaImagen;

    public Evento() {
    }

    public Evento(String nombre, String lugar, String descripcion, boolean activo, String rutaImagen) {
        this.descripcion = descripcion;
        this.activo = activo;
        this.rutaImagen = rutaImagen;
    }
}
