package com.productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    // Listar por Nombre
    List<Producto> findByNomProdContainingIgnoreCase(String nomProd);
    
    // Listar por Estado
    List<Producto> findByEstadoProd(Boolean estadoProd);
    
    // Listar de forma Ascendente (por nombre del producto)
    List<Producto> findByOrderByNomProdAsc();
    
    // Listar de forma Descendente (por nombre del producto)
    List<Producto> findByOrderByNomProdDesc();
}