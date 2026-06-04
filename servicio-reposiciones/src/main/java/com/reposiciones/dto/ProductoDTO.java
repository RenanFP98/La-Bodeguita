package com.reposiciones.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProd;
    private Integer idProv; // Lo necesitamos para auto-completarlo en la reposición
}
