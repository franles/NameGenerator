package com.example.generador_nombre.model;
import jakarta.persistence.*;

@Entity
@Table(name = "empresa_nombres") //nombre de la tabla MySQL

public class EmpresaNombre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera un Id para cada nombre generado

    private Long id;
    private String nombreGenerado;

    public EmpresaNombre() {}

    public EmpresaNombre(String nombreGenerado) {
        this.nombreGenerado = nombreGenerado;
    }

    public Long getId() {
        return id;
    }

    public String getNombreGenerado() {
        return nombreGenerado;
    }

    public void setNombreGenerado(String nombreGenerado) {
        this.nombreGenerado = nombreGenerado;
    }
}
