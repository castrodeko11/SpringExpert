package br.com.aco.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;



public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(String mensagemErro){
        this.erros = Arrays.asList(mensagemErro);
    }
}
