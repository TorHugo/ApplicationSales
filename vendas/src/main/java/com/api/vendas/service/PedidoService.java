package com.api.vendas.service;

import com.api.vendas.domain.entity.Pedido;
import com.api.vendas.domain.enums.StatusPedido;
import com.api.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoComplet(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
