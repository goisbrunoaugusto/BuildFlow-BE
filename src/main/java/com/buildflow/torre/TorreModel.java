package com.buildflow.torre;

import com.buildflow.obra.ObraModel;
import com.buildflow.pavimento.PavimentoModel;
import com.buildflow.torre.enums.TorreStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_torres")
public class TorreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "status_torre", nullable = false)
    @Enumerated(EnumType.STRING)
    private TorreStatus statusTorre;

    // TODO: Talvez remover essa coluna
    @Column(name = "pavimentos", nullable = false)
    private Integer pavimentos;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cei_obra", referencedColumnName = "cei", insertable = false, updatable = false)
    @JsonIgnore
    private ObraModel obra;

    @OneToMany(mappedBy = "torre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PavimentoModel> pavimentosList = new ArrayList<>();

    public TorreModel() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
        this.statusTorre = TorreStatus.PLANEJADA;
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

    public TorreStatus getStatusTorre() {
        return statusTorre;
    }

    public void setStatusTorre(TorreStatus statusTorre) {
        this.statusTorre = statusTorre;
    }

    public Integer getPavimentos() {
        return pavimentos;
    }

    public void setPavimentos(Integer pavimentos) {
        this.pavimentos = pavimentos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        this.statusTorre = TorreStatus.EM_CONSTRUCAO;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        this.statusTorre = TorreStatus.CONCLUIDA;
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

    public ObraModel getObra() {
        return obra;
    }

    public void setObra(ObraModel obra) {
        this.obra = obra;
    }

    public List<PavimentoModel> getPavimentosList() {
        return pavimentosList;
    }

    public void setPavimentosList(List<PavimentoModel> pavimentosList) {
        this.pavimentosList = pavimentosList;
    }

    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public boolean isEmAndamento() {
        LocalDate hoje = LocalDate.now();
        return dataInicio != null && dataInicio.isBefore(hoje) && 
               (dataFim == null || dataFim.isAfter(hoje));
    }

    public boolean isConcluida() {
        return dataFim != null && LocalDate.now().isAfter(dataFim);
    }

    public int getQuantidadePavimentos() {
        return pavimentosList != null ? pavimentosList.size() : 0;
    }

    public double getPercentualConclusao() {
        if (pavimentos == null || pavimentos == 0) {
            return 0.0;
        }
        return (double) getQuantidadePavimentos() / pavimentos * 100;
    }
}
