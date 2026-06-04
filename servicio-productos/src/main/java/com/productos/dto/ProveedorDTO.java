package com.productos.dto;

import lombok.Data;

@Data
public class ProveedorDTO {
    private Integer idProv;
    private String nomProv;
    private String telefono;
    private String ruc;
    private String correo;
    private String contacto;
    private Boolean estadoProv;
}
