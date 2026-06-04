package com.reposiciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Reposiciones")
public class Reposicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRepos")
    private Integer idRepos;

    @Column(name = "IdProd")
    private Integer idProd;

    @Column(name = "IdProv")
    private Integer idProv;

    @Column(name = "CantRepo")
    private Integer cantRepo;

    @Column(name = "FechaRepo")
    private LocalDateTime fechaRepo;

    @Column(name = "PrecioRepo")
    private Double precioRepo;

    @Column(name = "EstadoRepo")
    private Boolean estadoRepo = true;

    @Column(name = "IdUsuario") // Este es el ID del Empleado
    private Integer idUsuario;
}
