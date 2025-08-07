package com.buildflow.obra;

import com.buildflow.construtora.ConstrutoraModel;
import com.buildflow.equipe.EquipeModel;
import com.buildflow.torre.TorreModel;
import com.buildflow.usuario.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_obras")
public class ObraModel {

    @Id
    @Column(name = "cei", nullable = false, unique = true, length = 50)
    private String cei;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "db_obra_construtora",
            joinColumns = @JoinColumn(name = "obra_cei"),
            inverseJoinColumns = @JoinColumn(name = "construtora_id")
    )
    @JsonIgnore
    private List<ConstrutoraModel> construtoras = new ArrayList<>();

    @Column(name = "valor_m2", nullable = true, precision = 15, scale = 2)
    private BigDecimal valorM2;

    @Column(name = "total_geral", nullable = true, precision = 15, scale = 2)
    private BigDecimal totalGeral;

    @Column(name = "data_inicio", nullable = true)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "local", nullable = false, length = 500)
    private String local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_criador_id", nullable = false)
    private UsuarioModel usuarioCriador;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TorreModel> torres = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "db_obra_equipe",
            joinColumns = @JoinColumn(name = "obra_cei"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    @JsonIgnore
    private List<EquipeModel> equipes = new ArrayList<>();

    public ObraModel() {

        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public String getCei() {

        return cei;
    }

    public void setCei(String cei) {

        this.cei = cei;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public List<ConstrutoraModel> getConstrutoras() {
        return construtoras;
    }

    public void setConstrutoras(List<ConstrutoraModel> construtoras) {
        this.construtoras = construtoras;
    }

    public void addConstrutora(ConstrutoraModel construtora) {
        if (this.construtoras == null) {
            this.construtoras = new ArrayList<>();
        }
        this.construtoras.add(construtora);
    }

    public void removeConstrutora(ConstrutoraModel construtora) {
        if (this.construtoras != null) {
            this.construtoras.remove(construtora);
        }
    }

    public BigDecimal getValorM2() {

        return valorM2;
    }

    public void setValorM2(BigDecimal valorM2) {

        this.valorM2 = valorM2;
    }

    public BigDecimal getTotalGeral() {

        return totalGeral;
    }

    public void setTotalGeral(BigDecimal totalGeral) {

        this.totalGeral = totalGeral;
    }

    public LocalDate getDataInicio() {

        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {

        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {

        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {

        this.dataFim = dataFim;
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

    public UsuarioModel getUsuarioCriador() {

        return usuarioCriador;
    }

    public void setUsuarioCriador(UsuarioModel usuarioCriador) {

        this.usuarioCriador = usuarioCriador;
    }

    public List<TorreModel> getTorres() {

        return torres;
    }

    public void setTorres(List<TorreModel> torres) {

        this.torres = torres;
    }

    public List<EquipeModel> getEquipes() {

        return equipes;
    }

    public void setEquipes(List<EquipeModel> equipes) {

        this.equipes = equipes;
    }

    public String getLocal() {

        return local;
    }

    public void setLocal(String endereco) {

        this.local = endereco;
    }

    public boolean isAtivo() {

        return ativo != null && ativo;
    }

    public void setAtivo(Boolean ativo) {

        this.ativo = ativo;
    }

    public boolean isEmAndamento() {

        LocalDate hoje = LocalDate.now();
        return dataInicio != null && dataInicio.isBefore(hoje) &&
                (dataFim == null || dataFim.isAfter(hoje));
    }

    public boolean isConcluida() {

        return dataFim != null && LocalDate.now().isAfter(dataFim);
    }

}
