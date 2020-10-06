package com.example.pedido.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import com.example.pedido.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {

    private int aux;
    private List<Pedido> qtde_pedidos;

   @PostConstruct

   public void init() {
       
      Pedido pedido1= new Pedido(); 
        pedido1.setCodigo(1);
        pedido1.setCliente("Gabriel Ferraz");
        pedido1.setDescricao("Cachorrão Especial");
        pedido1.setValor(12.25);
        pedido1.setData_pedido("10/10/2020");

        Pedido pedido2= new Pedido();
        pedido2.setCodigo(2);
        pedido2.setCliente("Evelyn Morijo");
        pedido2.setDescricao("X-Salada");
        pedido2.setValor(13.00);
        pedido2.setData_pedido("10/10/2020");
    
    qtde_pedidos = new ArrayList<Pedido>();

    qtde_pedidos.add(pedido1);

    qtde_pedidos.add(pedido2);

    aux = 3;

    }

    public List<Pedido> getAllqtde_pedidos(){
        return qtde_pedidos;
    }

    public Pedido getPedidobyCodigo(int codigo){
        for (Pedido aux : qtde_pedidos) {
            if (aux.getCodigo() == codigo){
                return aux;
            }
        }

        return null;  }

	public Pedido salvar(Pedido pedido) {
        pedido.setCodigo(aux++);
        qtde_pedidos.add(pedido);
        return pedido;
	}

	public void remove(Pedido ped) {
        qtde_pedidos.remove(ped);
	}

	public Pedido update(Pedido pedido) {

        Pedido upt = getPedidobyCodigo(pedido.getCodigo());

        if (upt != null){
            
            upt.setCliente(pedido.getCliente());
            upt.setValor(pedido.getValor());
            upt.setDescricao(pedido.getDescricao());
            upt.setData_pedido(pedido.getData_pedido());
        }
           return upt;
        }
}