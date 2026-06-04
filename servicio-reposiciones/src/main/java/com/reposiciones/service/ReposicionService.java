package com.reposiciones.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposiciones.dto.ProductoDTO;
import com.reposiciones.feign.ProductoClient;
import com.reposiciones.model.Reposicion;
import com.reposiciones.repository.IReposicionRepository;

@Service
public class ReposicionService {

    @Autowired
    private IReposicionRepository reposicionRepository;

    @Autowired
    private ProductoClient productoClient;

    public Reposicion guardarReposicion(Reposicion nuevaReposicion) {
        // Buscamos el producto para obtener su proveedor suavemente
        try {
            ProductoDTO producto = productoClient.obtenerProductoPorId(nuevaReposicion.getIdProd());
            if (producto != null) {
                nuevaReposicion.setIdProv(producto.getIdProv());
            }
        } catch (Exception e) {
            // Si el servicio de productos no responde, podemos manejarlo aquí
            System.out.println("No se pudo obtener el proveedor del producto");
        }
        
        nuevaReposicion.setFechaRepo(LocalDateTime.now());
        nuevaReposicion.setEstadoRepo(true);
        return reposicionRepository.save(nuevaReposicion);
    }

    public boolean cancelarReposicion(Integer id) {
        Optional<Reposicion> repoOpt = reposicionRepository.findById(id);
        if (repoOpt.isPresent()) {
            Reposicion repo = repoOpt.get();
            repo.setEstadoRepo(false); // Eliminado lógico o "Cancelada"
            reposicionRepository.save(repo);
            return true;
        }
        return false;
    }

    // --- MÉTODOS DE LISTADO (Según tu imagen) ---

    public List<Reposicion> listarTodas() {
        return reposicionRepository.findAll();
    }

    public Optional<Reposicion> listarPorId(Integer id) {
        return reposicionRepository.findById(id);
    }

    public List<Reposicion> listarPorProducto(Integer idProd) {
        return reposicionRepository.findByIdProd(idProd);
    }

    public List<Reposicion> listarPorProveedor(Integer idProv) {
        return reposicionRepository.findByIdProv(idProv);
    }

    public List<Reposicion> listarPorEstado(Boolean estado) {
        return reposicionRepository.findByEstadoRepo(estado);
    }

    public List<Reposicion> listarPorEmpleado(Integer idUsuario) {
        return reposicionRepository.findByIdUsuario(idUsuario);
    }

    public List<Reposicion> listarAscendente() {
        return reposicionRepository.findAllByOrderByIdReposAsc();
    }

    public List<Reposicion> listarDescendente() {
        return reposicionRepository.findAllByOrderByIdReposDesc();
    }

    public List<Reposicion> listarCanceladas() {
        return reposicionRepository.findByEstadoRepo(false);
    }
}
