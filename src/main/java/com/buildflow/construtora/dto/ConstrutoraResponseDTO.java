package com.buildflow.construtora.dto;

import com.buildflow.construtora.ConstrutoraModel;

import java.time.LocalDateTime;

public class ConstrutoraResponseDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private String website;
    private LocalDateTime dataCriacao;
    private int quantidadeObras;
    private int quantidadeObrasAtivas;

    public ConstrutoraResponseDTO() {}

    public ConstrutoraResponseDTO(ConstrutoraModel construtora) {
        this.id = construtora.getId();
        this.nome = construtora.getNome();
        this.cnpj = construtora.getCnpj();
        this.endereco = construtora.getEndereco();
        this.telefone = construtora.getTelefone();
        this.email = construtora.getEmail();
        this.website = construtora.getWebsite();
        this.dataCriacao = construtora.getDataCriacao();
        this.quantidadeObras = construtora.getQuantidadeObras();
        this.quantidadeObrasAtivas = construtora.getQuantidadeObrasAtivas();
    }

    public ConstrutoraResponseDTO(Long id, String nome, String cnpj, String endereco, 
                                 String telefone, String email, String website, 
                                 LocalDateTime dataCriacao, 
                                 int quantidadeObras, int quantidadeObrasAtivas) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.website = website;
        this.dataCriacao = dataCriacao;
        this.quantidadeObras = quantidadeObras;
        this.quantidadeObrasAtivas = quantidadeObrasAtivas;
    }

    // Getters e Setters
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }



    public int getQuantidadeObras() {
        return quantidadeObras;
    }

    public void setQuantidadeObras(int quantidadeObras) {
        this.quantidadeObras = quantidadeObras;
    }

    public int getQuantidadeObrasAtivas() {
        return quantidadeObrasAtivas;
    }

    public void setQuantidadeObrasAtivas(int quantidadeObrasAtivas) {
        this.quantidadeObrasAtivas = quantidadeObrasAtivas;
    }
} 