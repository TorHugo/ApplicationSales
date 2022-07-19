package com.api.vendas.rest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter //gera um get
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
    public ApiErrors(String mesagemErro){
        this.errors = Arrays.asList(mesagemErro);
    }
}
