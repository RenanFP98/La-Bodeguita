package com.reposiciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reposiciones.model.Reposicion;
import com.reposiciones.service.ReposicionService;

@RestController
@RequestMapping("/api/reposiciones")
public class ReposicionController {

    @Autowired
    private ReposicionService reposicionService;

    @PostMapping
    public ResponseEntity<Reposicion> crear(@RequestBody Reposicion reposicion) {
        return ResponseEntity.ok(reposicionService.guardarReposicion(reposicion));
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        boolean cancelada = reposicionService.cancelarReposicion(id);
        return cancelada ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // --- TUS ENDPOINTS GET ---

    @GetMapping
    public ResponseEntity<List<Reposicion>> listarTodas() {
        return ResponseEntity.ok(reposicionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reposicion> listarPorId(@PathVariable Integer id) {
        return reposicionService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{idProd}")
    public ResponseEntity<List<Reposicion>> listarPorProducto(@PathVariable Integer idProd) {
        return ResponseEntity.ok(reposicionService.listarPorProducto(idProd));
    }

    @GetMapping("/proveedor/{idProv}")
    public ResponseEntity<List<Reposicion>> listarPorProveedor(@PathVariable Integer idProv) {
        return ResponseEntity.ok(reposicionService.listarPorProveedor(idProv));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reposicion>> listarPorEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(reposicionService.listarPorEstado(estado));
    }

    @GetMapping("/empleado/{idUsuario}")
    public ResponseEntity<List<Reposicion>> listarPorEmpleado(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(reposicionService.listarPorEmpleado(idUsuario));
    }

    @GetMapping("/orden/asc")
    public ResponseEntity<List<Reposicion>> listarAscendente() {
        return ResponseEntity.ok(reposicionService.listarAscendente());
    }

    @GetMapping("/orden/desc")
    public ResponseEntity<List<Reposicion>> listarDescendente() {
        return ResponseEntity.ok(reposicionService.listarDescendente());
    }

    @GetMapping("/canceladas")
    public ResponseEntity<List<Reposicion>> listarCanceladas() {
        return ResponseEntity.ok(reposicionService.listarCanceladas());
    }
}
