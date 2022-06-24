package br.com.aco.service;

import br.com.aco.domain.entity.Pedido;
import br.com.aco.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO pedidoDTO);
}
