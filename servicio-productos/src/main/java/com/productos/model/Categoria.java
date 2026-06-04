package com.productos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCateg")
    private Integer idCateg;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "EstadoCateg")
    private Boolean estadoCateg = true; // Por defecto activo al crear
}
