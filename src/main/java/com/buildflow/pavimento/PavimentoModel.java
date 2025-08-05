package com.buildflow.pavimento;

import com.buildflow.atividade.AtividadeModel;
import com.buildflow.pavimento.enums.PavimentoStatus;
import com.buildflow.torre.TorreModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_pavimentos")
public class PavimentoModel {
    // TODO: Alinhar se o pavimento terá um nome fixo ou será um nome dinâmico
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "area_pavimento", nullable = false, precision = 10, scale = 2)
    private BigDecimal areaPavimento = BigDecimal.ZERO;

    @Column(name = "area_executada", nullable = false, precision = 10, scale = 2)
    private BigDecimal areaExecutada = BigDecimal.ZERO;

    @Column(name = "m3_total_argamassa", nullable = false, precision = 10, scale = 2)
    private BigDecimal m3TotalArgamassa = BigDecimal.ZERO;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torre_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private TorreModel torre;

    @OneToMany(mappedBy = "pavimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AtividadeModel> atividades = new ArrayList<>();

    public PavimentoModel() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
        this.areaExecutada = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAreaPavimento() {
        return areaPavimento;
    }

    public void setAreaPavimento(BigDecimal areaPavimento) {
        this.areaPavimento = areaPavimento;
    }

    public BigDecimal getAreaExecutada() {
        return areaExecutada;
    }

    public void setAreaExecutada(BigDecimal areaExecutada) {
        this.areaExecutada = areaExecutada;
    }

    public BigDecimal getM3TotalArgamassa() {
        return m3TotalArgamassa;
    }

    public void setM3TotalArgamassa(BigDecimal m3TotalArgamassa) {
        this.m3TotalArgamassa = m3TotalArgamassa;
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

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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

    public TorreModel getTorre() {
        return torre;
    }

    public void setTorre(TorreModel torre) {
        this.torre = torre;
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

    public PavimentoStatus getStatusPavimento() {
        if (areaPavimento == null || areaPavimento.compareTo(BigDecimal.ZERO) == 0) {
            return PavimentoStatus.PLANEJADO;
        }
        
        if (areaExecutada.compareTo(areaPavimento) >= 0) {
            return PavimentoStatus.CONCLUIDO;
        } else if (areaExecutada.compareTo(BigDecimal.ZERO) > 0) {
            return PavimentoStatus.EM_EXECUCAO;
        } else {
            return PavimentoStatus.PLANEJADO;
        }
    }

    public BigDecimal getEspessuraArgamassa() {
        if (areaPavimento == null || areaPavimento.compareTo(BigDecimal.ZERO) == 0 || 
            m3TotalArgamassa == null || m3TotalArgamassa.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return m3TotalArgamassa.divide(areaPavimento, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal getPercentualExecutado() {
        if (areaPavimento == null || areaPavimento.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return areaExecutada.divide(areaPavimento, RoundingMode.HALF_UP)
                           .multiply(new BigDecimal("100"))
                           .setScale(4, RoundingMode.HALF_UP);
    }

    public boolean isEmAndamento() {
        LocalDate hoje = LocalDate.now();
        return dataInicio != null && dataInicio.isBefore(hoje) && 
               (dataFim == null || dataFim.isAfter(hoje));
    }

    public boolean isConcluido() {
        return dataFim != null && LocalDate.now().isAfter(dataFim);
    }

    public void adicionarM2Executada(BigDecimal m2Adicional) {
        if (m2Adicional != null && m2Adicional.compareTo(BigDecimal.ZERO) > 0) {
            this.areaExecutada = this.areaExecutada.add(m2Adicional);
        }
    }

    public void adicionarM3TotalArgamassa(BigDecimal m3Adicional) {
        if (m3Adicional != null && m3Adicional.compareTo(BigDecimal.ZERO) > 0) {
            this.m3TotalArgamassa = this.m3TotalArgamassa.add(m3Adicional);
        }
    }
}
