package com.proveedores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proveedores.model.Proveedor;
import com.proveedores.repository.IProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private IProveedorRepository proveedorRepository;

    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizarProveedor(Integer id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNomProv(proveedorActualizado.getNomProv());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
            proveedor.setRuc(proveedorActualizado.getRuc());
            proveedor.setCorreo(proveedorActualizado.getCorreo());
            proveedor.setContacto(proveedorActualizado.getContacto());
            return proveedorRepository.save(proveedor);
        }).orElse(null);
    }

    // Borrado lógico con delicadeza
    public boolean eliminarLogicoProveedor(Integer id) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedor = proveedorOpt.get();
            proveedor.setEstadoProv(false); // Solo lo desactivamos
            proveedorRepository.save(proveedor);
            return true;
        }
        return false;
    }

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> listarPorId(Integer id) {
        return proveedorRepository.findById(id);
    }

    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNomProvContainingIgnoreCase(nombre);
    }

    public List<Proveedor> buscarPorEstado(Boolean estado) {
        return proveedorRepository.findByEstadoProv(estado);
    }

    public List<Proveedor> listarAscendente() {
        return proveedorRepository.findAllByOrderByIdProvAsc();
    }

    public List<Proveedor> listarDescendente() {
        return proveedorRepository.findAllByOrderByIdProvDesc();
    }
}
