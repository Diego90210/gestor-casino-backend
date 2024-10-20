package com.diego.gestorcasino.models;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nit;

    @Column(nullable = false)
    private String nombre;

    // Getters y Setters

    public Long getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nit=" + nit +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
