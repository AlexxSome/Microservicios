package cl.greendev.productos.controllers;

import cl.greendev.productos.models.entity.Producto;
import cl.greendev.productos.models.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer  port;

    @GetMapping("/listar")
    public List<Producto> listar(){
        return productoService.findAll().stream().map(prod ->{
            prod.setPort(port);
            return prod;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) throws Exception {
        Producto prod = productoService.findById(id);
        prod.setPort(port);
        boolean ok = false;
        if(ok == false){
            throw new Exception("No se puede cargar el PROD");
        }

        return prod;
    }
}
