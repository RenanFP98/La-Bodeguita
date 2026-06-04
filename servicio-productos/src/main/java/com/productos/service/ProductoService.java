package com.productos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productos.dto.ProveedorDTO;
import com.productos.feign.ProveedorClient;
import com.productos.model.Producto;
import com.productos.repository.IProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    
    @Autowired
    private ProveedorClient proveedorClient;

    // --- CRUD BÁSICO ---

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Integer id, Producto productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNomProd(productoActualizado.getNomProd());
            producto.setStockMax(productoActualizado.getStockMax());
            producto.setStockMin(productoActualizado.getStockMin());
            producto.setStockActual(productoActualizado.getStockActual());
            producto.setPrecioUnit(productoActualizado.getPrecioUnit());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setIdProv(productoActualizado.getIdProv());
            return productoRepository.save(producto);
        }).orElse(null); // Si no lo encuentra, retorna null
    }

    // ELIMINADO LÓGICO (Con mucha delicadeza, solo cambiamos su estado)
    public boolean eliminarLogicoProducto(Integer id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setEstadoProd(false); // Lo desactivamos
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    // --- MÉTODOS DE LISTADO (Según tu imagen) ---

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> listarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> listarPorNombre(String nombre) {
        return productoRepository.findByNomProdContainingIgnoreCase(nombre);
    }

    public List<Producto> listarPorEstado(Boolean estado) {
        return productoRepository.findByEstadoProd(estado);
    }

    public List<Producto> listarAscendente() {
        return productoRepository.findByOrderByNomProdAsc();
    }

    public List<Producto> listarDescendente() {
        return productoRepository.findByOrderByNomProdDesc();
    }
    
    public Map<String, Object> obtenerProductoConSuProveedor(Integer idProd) {
        Map<String, Object> respuesta = new HashMap<>();
        
        Optional<Producto> productoOpt = productoRepository.findById(idProd);
        
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            respuesta.put("producto", producto);
            
            // Aquí ocurre la magia: le pedimos la info al otro microservicio suavecito
            if (producto.getIdProv() != null) {
                try {
                    ProveedorDTO proveedor = proveedorClient.obtenerProveedorPorId(producto.getIdProv());
                    respuesta.put("proveedor", proveedor);
                } catch (Exception e) {
                    // Si el otro servicio está apagado o falla, no se cae nuestro programa, solo avisamos
                    respuesta.put("proveedor", "El servicio de proveedores no está disponible en este momento");
                }
            } else {
                respuesta.put("proveedor", "Este producto aún no tiene un proveedor asignado");
            }
        }
        
        return respuesta;
    }
    
}
