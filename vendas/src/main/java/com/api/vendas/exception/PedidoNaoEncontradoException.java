package com.api.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }
}
