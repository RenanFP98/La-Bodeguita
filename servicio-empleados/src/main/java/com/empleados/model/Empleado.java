package com.empleados.model;

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
@Table(name = "Empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
    private Integer idEmpleado;

    @Column(name = "Username")
    private String username;

    @Column(name = "NomUsua")
    private String nomUsua;

    @Column(name = "ApellUsua")
    private String apellUsua;

    @Column(name = "Contraseña") // Se creará así en la base de datos
    private String contrasena;   // Lo manejamos así en Java por seguridad

    @Column(name = "Dni")
    private String dni;

    @Column(name = "EstadoUsua")
    private Boolean estadoUsua = true;

    @ManyToOne
    @JoinColumn(name = "IdRol")
    private Rol rol;
}