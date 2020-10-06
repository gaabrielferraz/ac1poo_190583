package com.example.pedido.controllers;

import com.example.pedido.model.Pedido;
import com.example.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public List<Pedido> getPedidos() {
        return repository.getAllqtde_pedidos();
    }    

    @GetMapping("/{codigo}")       
    public ResponseEntity<Pedido> getPedidoByCodigo(@PathVariable int codigo) {
        
        Pedido ped = repository.getPedidobyCodigo(codigo);
        if( ped != null) {
            return ResponseEntity.ok(ped);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping

    public ResponseEntity<Void>salvar(@RequestBody Pedido pedido) {
        Pedido ped = repository.salvar(pedido);
        URI uri = URI.create("https://localhost:8080/pedidos/"+ ped.getCodigo() );
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable int codigo) {
     
        Pedido ped = repository.getPedidobyCodigo(codigo);
        if ( ped != null ){
            repository.remove(ped);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> atualizar(@PathVariable int codigo, @RequestBody Pedido pedido){
            if (repository.getPedidobyCodigo(codigo) != null){
                pedido.setCodigo(codigo);
                pedido = repository.update(pedido);
                return ResponseEntity.ok(pedido);
            } else{
                return ResponseEntity.notFound().build();
            }
    }


    
}