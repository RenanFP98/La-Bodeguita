package com.reposiciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reposiciones.model.Reposicion;

public interface IReposicionRepository extends JpaRepository<Reposicion, Integer>{

List<Reposicion> findByIdProd(Integer idProd);
    
    List<Reposicion> findByIdProv(Integer idProv);
    
    List<Reposicion> findByIdUsuario(Integer idUsuario);
    
    List<Reposicion> findByEstadoRepo(Boolean estadoRepo);
    
    List<Reposicion> findAllByOrderByIdReposAsc();
    
    List<Reposicion> findAllByOrderByIdReposDesc();
	
}
