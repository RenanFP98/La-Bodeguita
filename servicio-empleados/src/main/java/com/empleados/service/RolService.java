package com.empleados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.model.Rol;
import com.empleados.repository.IRolRepository;

@Service
public class RolService {

    @Autowired
    private IRolRepository rolRepository;

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol actualizarRol(Integer id, Rol rolActualizado) {
        return rolRepository.findById(id).map(rol -> {
            rol.setNomRol(rolActualizado.getNomRol());
            return rolRepository.save(rol);
        }).orElse(null);
    }

    public boolean eliminarLogicoRol(Integer id) {
        Optional<Rol> rolOpt = rolRepository.findById(id);
        if (rolOpt.isPresent()) {
            Rol rol = rolOpt.get();
            rol.setEstadoRol(false);
            rolRepository.save(rol);
            return true;
        }
        return false;
    }

    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> listarPorId(Integer id) {
        return rolRepository.findById(id);
    }

    public List<Rol> buscarPorNombre(String nombre) {
        return rolRepository.findByNomRolContainingIgnoreCase(nombre);
    }

    public List<Rol> buscarPorEstado(Boolean estado) {
        return rolRepository.findByEstadoRol(estado);
    }

    public List<Rol> listarAscendente() {
        return rolRepository.findAllByOrderByNomRolAsc();
    }

    public List<Rol> listarDescendente() {
        return rolRepository.findAllByOrderByNomRolDesc();
    }
    
}
