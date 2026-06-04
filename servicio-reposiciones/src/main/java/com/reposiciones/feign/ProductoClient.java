package com.reposiciones.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reposiciones.dto.ProductoDTO;

// El nombre debe ser igual a como llamamos al microservicio de productos
@FeignClient(name = "servicio-productos")
public interface ProductoClient {

	@GetMapping("/api/productos/{id}")
	ProductoDTO obtenerProductoPorId(@PathVariable("id") Integer id);
}