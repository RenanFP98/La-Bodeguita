package com.empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empleados.model.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
	// Buscar por Nombre
    List<Rol> findByNomRolContainingIgnoreCase(String nomRol);
    
    // Buscar por Estado
    List<Rol> findByEstadoRol(Boolean estadoRol);
    
    // Listar Ascendente (por nombre)
    List<Rol> findAllByOrderByNomRolAsc();
    
    // Listar Descendente (por nombre)
    List<Rol> findAllByOrderByNomRolDesc();
}
