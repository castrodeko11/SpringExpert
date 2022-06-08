package br.com.aco;


import br.com.aco.domain.entity.Cliente;
import br.com.aco.domain.entity.Pedido;
import br.com.aco.domain.repository.Clientes;
import br.com.aco.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class VendasApplication {

    @Autowired
    private Clientes clientes1;

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos) {

        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Dougllas"));
            clientes.save(new Cliente("Outro Cliente"));

            Cliente fulano = new Cliente("Andre");
            clientes.save(fulano);

            Pedido pedido = new Pedido();
            pedido.setCliente(fulano);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));

            pedidos.save(pedido);

//            boolean existe = clientes.existsByNome("Dougllas");
//            System.out.println("Existe um cliente com o nome Dougllas: " + existe);

//            List<Cliente> result = clientes.findByNomeLike("Dougllas");
//            result.forEach(System.out::println);

//
//            Cliente clientePedidoFetch = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(clientePedidoFetch);
//            System.out.println(clientePedidoFetch.getPedidos());

            pedidos.findByCliente(fulano).forEach(System.out::println);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
