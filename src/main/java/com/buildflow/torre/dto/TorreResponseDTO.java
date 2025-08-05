package com.buildflow.torre.dto;

import com.buildflow.torre.TorreModel;
import com.buildflow.torre.enums.TorreStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TorreResponseDTO {

    private Long id;
    private String nome;
    private TorreStatus statusTorre;
    private Integer pavimentos;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime dataCriacao;
    private Boolean ativo;
    private String ceiObra;
    private String nomeObra;
    private int quantidadePavimentos;
    private double percentualConclusao;
    private boolean emAndamento;
    private boolean concluida;

    public TorreResponseDTO() {}

    public TorreResponseDTO(TorreModel torre) {
        this.id = torre.getId();
        this.nome = torre.getNome();
        this.statusTorre = torre.getStatusTorre();
        this.pavimentos = torre.getPavimentos();
        this.dataInicio = torre.getDataInicio();
        this.dataFim = torre.getDataFim();
        this.dataCriacao = torre.getDataCriacao();
        this.ativo = torre.isAtivo();
        this.ceiObra = torre.getObra() != null ? torre.getObra().getCei() : null;
        this.nomeObra = torre.getObra() != null ? torre.getObra().getNome() : null;
        this.quantidadePavimentos = torre.getQuantidadePavimentos();
        this.percentualConclusao = torre.getPercentualConclusao();
        this.emAndamento = torre.isEmAndamento();
        this.concluida = torre.isConcluida();
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

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public int getQuantidadePavimentos() {
        return quantidadePavimentos;
    }

    public void setQuantidadePavimentos(int quantidadePavimentos) {
        this.quantidadePavimentos = quantidadePavimentos;
    }

    public double getPercentualConclusao() {
        return percentualConclusao;
    }

    public void setPercentualConclusao(double percentualConclusao) {
        this.percentualConclusao = percentualConclusao;
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
} 