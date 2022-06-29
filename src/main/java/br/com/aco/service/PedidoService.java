package br.com.aco.service;

import br.com.aco.domain.entity.Pedido;
import br.com.aco.domain.enums.StatusPedido;
import br.com.aco.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
