package com.buildflow.equipe;

import com.buildflow.atividade.AtividadeModel;
import com.buildflow.obra.ObraModel;
import com.buildflow.usuario.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_equipes")
public class EquipeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "db_equipe_usuario",
        joinColumns = @JoinColumn(name = "equipe_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    @JsonIgnore
    private List<UsuarioModel> usuarios = new ArrayList<>();

    @ManyToMany(mappedBy = "equipes", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ObraModel> obras = new ArrayList<>();

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AtividadeModel> atividades = new ArrayList<>();

    public EquipeModel() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ObraModel> getObras() {
        return obras;
    }

    public void setObras(List<ObraModel> obras) {
        this.obras = obras;
    }

    public List<AtividadeModel> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeModel> atividades) {
        this.atividades = atividades;
    }

    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public int getQuantidadeUsuarios() {
        return usuarios != null ? usuarios.size() : 0;
    }

    public boolean adicionarUsuario(UsuarioModel usuario) {
        if (usuario != null && !usuarios.contains(usuario)) {
            return usuarios.add(usuario);
        }
        return false;
    }

    public boolean removerUsuario(UsuarioModel usuario) {
        return usuarios.remove(usuario);
    }
}
