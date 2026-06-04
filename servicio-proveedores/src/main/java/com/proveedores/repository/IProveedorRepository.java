package com.proveedores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proveedores.model.Proveedor;

public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {

	// Buscar por nombre del proveedor (NomProv)
    List<Proveedor> findByNomProvContainingIgnoreCase(String nomProv);

    // Buscar por estado
    List<Proveedor> findByEstadoProv(Boolean estadoProv);

    // Listar de forma ascendente (por ID, como lo tenía tu compañero)
    List<Proveedor> findAllByOrderByIdProvAsc();

    // Listar de forma descendente (por ID)
    List<Proveedor> findAllByOrderByIdProvDesc();
	
}
