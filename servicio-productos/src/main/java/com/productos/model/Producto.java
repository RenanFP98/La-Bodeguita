package com.productos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProd")
    private Integer idProd;

    @Column(name = "NomProd")
    private String nomProd;

    @Column(name = "StockMax")
    private Integer stockMax;

    @Column(name = "StockMin")
    private Integer stockMin;

    @Column(name = "StockActual")
    private Integer stockActual;

    @Column(name = "PrecioUnit")
    private Double precioUnit;

    @Column(name = "EstadoProd")
    private Boolean estadoProd = true;

    @Column(name = "IdProv")
    private Integer idProv; // Aquí solo guardamos el ID para usar Feign después

    @ManyToOne
    @JoinColumn(name = "IdCateg")
    private Categoria categoria; 
}