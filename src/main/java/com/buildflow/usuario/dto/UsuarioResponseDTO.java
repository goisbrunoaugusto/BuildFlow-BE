package com.buildflow.usuario.dto;

import com.buildflow.usuario.UsuarioModel;
import com.buildflow.usuario.enums.UsuarioFuncao;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private UsuarioFuncao funcao;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private String nomeCompleto;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(UsuarioModel usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.email = usuario.getEmail();
        this.funcao = usuario.getFuncao();
        this.ativo = usuario.isAtivo();
        this.dataCriacao = usuario.getDataCriacao();
        this.nomeCompleto = usuario.getNomeCompleto();
    }

    // Getters and Setters
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuarioFuncao getFuncao() {
        return funcao;
    }

    public void setFuncao(UsuarioFuncao funcao) {
        this.funcao = funcao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
} 