package com.buildflow.usuario;

import com.buildflow.usuario.enums.UsuarioFuncao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "db_usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 100)
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "senha", nullable = false)
    @JsonIgnore
    private String senha;

    @Column(name = "funcao", nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioFuncao funcao;

    @Column(name = "ativo", nullable = false)
    private Boolean is_ativo = true;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    public UsuarioModel() {

        this.dataCriacao = LocalDateTime.now();
        this.is_ativo = true;
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

    public void setSenha(String senha) {

        this.senha = senha;
    }

    public UsuarioFuncao getFuncao() {

        return funcao;
    }

    public void setFuncao(UsuarioFuncao funcao) {

        this.funcao = funcao;
    }

    public Boolean getAtivo() {

        return is_ativo;
    }

    public LocalDateTime getDataCriacao() {

        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {

        this.dataCriacao = dataCriacao;
    }

    public String getNomeCompleto() {

        return nome + " " + sobrenome;
    }

    public boolean isAtivo() {

        return is_ativo != null && is_ativo;
    }

    public void setAtivo(Boolean ativo) {

        this.is_ativo = ativo;
    }
}
