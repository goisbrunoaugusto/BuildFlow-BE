package com.buildflow.equipe.dto;

import jakarta.validation.constraints.Size;

public class EquipeUpdateDTO {

    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
} 