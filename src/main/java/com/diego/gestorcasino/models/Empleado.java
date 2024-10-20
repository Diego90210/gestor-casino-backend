package com.diego.gestorcasino.models;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cedula;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)  // Relación con la empresa
    @JoinColumn(name = "empresa_nit", referencedColumnName = "nit")
    private Empresa empresa;

    // Getters y Setters

    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", empresa=" + empresa +
                '}';
    }
}

