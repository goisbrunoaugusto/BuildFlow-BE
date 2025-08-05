package com.buildflow.pavimento.dto;

import com.buildflow.pavimento.PavimentoModel;
import com.buildflow.pavimento.enums.PavimentoStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PavimentoResponseDTO {

    private Long id;
    private BigDecimal areaPavimento;
    private BigDecimal areaExecutada;
    private BigDecimal m3TotalArgamassa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacoes;
    private LocalDateTime dataCriacao;
    private Boolean ativo;
    private Long torreId;
    private String nomeTorre;
    private String ceiObra;
    private String nomeObra;
    private PavimentoStatus statusPavimento;
    private BigDecimal espessuraArgamassa;
    private BigDecimal percentualExecutado;
    private boolean emAndamento;
    private boolean concluido;

    public PavimentoResponseDTO() {}

    public PavimentoResponseDTO(PavimentoModel pavimento) {
        this.id = pavimento.getId();
        this.areaPavimento = pavimento.getAreaPavimento();
        this.areaExecutada = pavimento.getAreaExecutada();
        this.m3TotalArgamassa = pavimento.getM3TotalArgamassa();
        this.dataInicio = pavimento.getDataInicio();
        this.dataFim = pavimento.getDataFim();
        this.observacoes = pavimento.getObservacoes();
        this.dataCriacao = pavimento.getDataCriacao();
        this.ativo = pavimento.isAtivo();
        this.torreId = pavimento.getTorre() != null ? pavimento.getTorre().getId() : null;
        this.nomeTorre = pavimento.getTorre() != null ? pavimento.getTorre().getNome() : null;
        this.ceiObra = pavimento.getTorre() != null && pavimento.getTorre().getObra() != null ? 
                      pavimento.getTorre().getObra().getCei() : null;
        this.nomeObra = pavimento.getTorre() != null && pavimento.getTorre().getObra() != null ? 
                       pavimento.getTorre().getObra().getNome() : null;
        this.statusPavimento = pavimento.getStatusPavimento();
        this.espessuraArgamassa = pavimento.getEspessuraArgamassa();
        this.percentualExecutado = pavimento.getPercentualExecutado();
        this.emAndamento = pavimento.isEmAndamento();
        this.concluido = pavimento.isConcluido();
    }

    // Getters and Setters
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

    public Long getTorreId() {
        return torreId;
    }

    public void setTorreId(Long torreId) {
        this.torreId = torreId;
    }

    public String getNomeTorre() {
        return nomeTorre;
    }

    public void setNomeTorre(String nomeTorre) {
        this.nomeTorre = nomeTorre;
    }

    public String getCeiObra() {
        return ceiObra;
    }

    public void setCeiObra(String ceiObra) {
        this.ceiObra = ceiObra;
    }

    public String getNomeObra() {
        return nomeObra;
    }

    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }

    public PavimentoStatus getStatusPavimento() {
        return statusPavimento;
    }

    public void setStatusPavimento(PavimentoStatus statusPavimento) {
        this.statusPavimento = statusPavimento;
    }

    public BigDecimal getEspessuraArgamassa() {
        return espessuraArgamassa;
    }

    public void setEspessuraArgamassa(BigDecimal espessuraArgamassa) {
        this.espessuraArgamassa = espessuraArgamassa;
    }

    public BigDecimal getPercentualExecutado() {
        return percentualExecutado;
    }

    public void setPercentualExecutado(BigDecimal percentualExecutado) {
        this.percentualExecutado = percentualExecutado;
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
} 