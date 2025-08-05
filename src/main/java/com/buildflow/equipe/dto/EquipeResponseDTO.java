package com.buildflow.equipe.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EquipeResponseDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private boolean ativo;
    private int quantidadeUsuarios;
    private List<String> usuariosNomes;

    public EquipeResponseDTO() {}

    public EquipeResponseDTO(Long id, String nome, LocalDateTime dataCriacao, boolean ativo, 
                            int quantidadeUsuarios, List<String> usuariosNomes) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.quantidadeUsuarios = quantidadeUsuarios;
        this.usuariosNomes = usuariosNomes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getQuantidadeUsuarios() {
        return quantidadeUsuarios;
    }

    public void setQuantidadeUsuarios(int quantidadeUsuarios) {
        this.quantidadeUsuarios = quantidadeUsuarios;
    }

    public List<String> getUsuariosNomes() {
        return usuariosNomes;
    }

    public void setUsuariosNomes(List<String> usuariosNomes) {
        this.usuariosNomes = usuariosNomes;
    }
} 