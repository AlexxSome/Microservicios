package cl.greendev.item.models.service;

import cl.greendev.item.models.Item;
import cl.greendev.item.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
        return productos.stream().map(prod->new Item(prod,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());
        Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariable);
        return new Item(producto,cantidad);
    }
}
