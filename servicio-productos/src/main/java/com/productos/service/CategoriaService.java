package com.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productos.model.Categoria;
import com.productos.repository.ICategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    // --- CRUD BÁSICO ---

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Integer id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaActualizada.getNombre());
            // No tocamos el estado aquí para que se maneje por separado
            return categoriaRepository.save(categoria);
        }).orElse(null);
    }

    // ELIMINADO LÓGICO (Cambiamos el estado a false para no perder la información)
    public boolean eliminarLogicoCategoria(Integer id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setEstadoCateg(false); 
            categoriaRepository.save(categoria);
            return true;
        }
        return false;
    }

    // --- MÉTODOS DE LISTADO ---

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> listarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> listarPorNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Categoria> listarPorEstado(Boolean estado) {
        return categoriaRepository.findByEstadoCateg(estado);
    }

    public List<Categoria> listarAscendente() {
        return categoriaRepository.findByOrderByNombreAsc();
    }

    public List<Categoria> listarDescendente() {
        return categoriaRepository.findByOrderByNombreDesc();
    }
}