package com.buildflow.usuario.dto;

import com.buildflow.usuario.enums.UsuarioFuncao;
import jakarta.validation.constraints.*;

public class UsuarioUpdateDTO {

    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 100, message = "Sobrenome deve ter no máximo 100 caracteres")
    private String sobrenome;

    @Email(message = "Email deve ter um formato válido")
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    private String email;

    @Size(min = 6, max = 255, message = "Senha deve ter entre 6 e 255 caracteres")
    private String senha;

    private UsuarioFuncao funcao;

    private Boolean ativo;

    // Getters and Setters
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

    public String getSenha() {
        return senha;
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
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
} 