package com.empleados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empleados.model.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {
	// La búsqueda para tu futuro login
    Optional<Empleado> findByUsername(String username); 
    
    // Buscar por Nombre (usamos el nomUsua)
    List<Empleado> findByNomUsuaContainingIgnoreCase(String nomUsua);
    
    // Buscar por Estado
    List<Empleado> findByEstadoUsua(Boolean estadoUsua);
    
    // Listar Ascendente (por nombre)
    List<Empleado> findAllByOrderByNomUsuaAsc();
    
    // Listar Descendente (por nombre)
    List<Empleado> findAllByOrderByNomUsuaDesc();
    
    
}
