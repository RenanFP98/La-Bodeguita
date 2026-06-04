package com.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empleados.model.Rol;
import com.empleados.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> crear(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.guardarRol(rol));
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarTodos() {
        return ResponseEntity.ok(rolService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Integer id, @RequestBody Rol rol) {
        Rol actualizado = rolService.actualizarRol(id, rol);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Integer id) {
        boolean eliminado = rolService.eliminarLogicoRol(id);
        return eliminado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> listarPorId(@PathVariable Integer id) {
        return rolService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Rol>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(rolService.buscarPorNombre(nombre));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Rol>> buscarPorEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(rolService.buscarPorEstado(estado));
    }

    @GetMapping("/orden/asc")
    public ResponseEntity<List<Rol>> listarAscendente() {
        return ResponseEntity.ok(rolService.listarAscendente());
    }

    @GetMapping("/orden/desc")
    public ResponseEntity<List<Rol>> listarDescendente() {
        return ResponseEntity.ok(rolService.listarDescendente());
    }
    
}