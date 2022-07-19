package com.api.vendas.domain.repository;

import com.api.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {
}
