package com.buildflow.construtora;

import com.buildflow.obra.ObraModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_construtoras")
public class ConstrutoraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true, length = 255)
    @NotBlank(message = "Nome da construtora é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    private String nome;

    @Column(name = "cnpj", length = 18)
    @Size(max = 18, message = "CNPJ deve ter no máximo 18 caracteres")
    private String cnpj;

    @Column(name = "endereco", length = 500)
    @Size(max = 500, message = "Endereço deve ter no máximo 500 caracteres")
    private String endereco;

    @Column(name = "telefone", length = 20)
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Column(name = "email", length = 255)
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    private String email;

    @Column(name = "website", length = 255)
    @Size(max = 255, message = "Website deve ter no máximo 255 caracteres")
    private String website;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToMany(mappedBy = "construtoras", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ObraModel> obras = new ArrayList<>();

    public ConstrutoraModel() {
        this.dataCriacao = LocalDateTime.now();
    }

    public ConstrutoraModel(String nome) {
        this();
        this.nome = nome;
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

    public List<ObraModel> getObras() {
        return obras;
    }

    public void setObras(List<ObraModel> obras) {
        this.obras = obras;
    }

    public int getQuantidadeObras() {
        return obras != null ? obras.size() : 0;
    }

    public int getQuantidadeObrasAtivas() {
        return obras != null ? (int) obras.stream()
                .filter(ObraModel::isAtivo)
                .count() : 0;
    }
} 