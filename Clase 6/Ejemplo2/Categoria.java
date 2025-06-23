package com.bedu.inventario;

import jakarta.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    protected Categoria() {
        // Constructor por defecto requerido por JPA
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return String.format("Categoria[id=%d, nombre='%s']", id, nombre);
    }
}
