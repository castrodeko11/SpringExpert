package br.com.aco.service.impl;

import br.com.aco.domain.entity.Cliente;
import br.com.aco.domain.entity.ItemPedido;
import br.com.aco.domain.entity.Pedido;
import br.com.aco.domain.entity.Produto;
import br.com.aco.domain.repository.Clientes;
import br.com.aco.domain.repository.ItensPedido;
import br.com.aco.domain.repository.Pedidos;
import br.com.aco.domain.repository.Produtos;
import br.com.aco.exception.RegraNegocioException;
import br.com.aco.rest.dto.ItemPedidoDTO;
import br.com.aco.rest.dto.PedidoDTO;
import br.com.aco.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidos;
    private final Clientes clientes;
    private final Produtos produtos;

    private final ItensPedido itensPedidos;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientes
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedidos = converterItems(pedido, pedidoDTO.getItens());
        pedidos.save(pedido);
        itensPedidos.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOList) {
        if (itemPedidoDTOList.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items");
        }

        return itemPedidoDTOList
                .stream()
                .map(itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtos
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto
                            ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedido.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
