package com.proveedores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProv")
    private Integer idProv;

    @Column(name = "NomProv")
    private String nomProv;

    @Column(name = "Teléfono") // Respetamos el nombre exacto de tu diagrama
    private String telefono;

    @Column(name = "Ruc")
    private String ruc;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Contacto")
    private String contacto;

    @Column(name = "EstadoProv")
    private Boolean estadoProv = true; // Activo por defecto
}
