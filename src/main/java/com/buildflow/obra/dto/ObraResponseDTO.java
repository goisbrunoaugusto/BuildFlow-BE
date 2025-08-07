package com.buildflow.obra.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObraResponseDTO {

    private String cei;
    private String nome;
    private String construtoraNome;
    private Long construtoraId;
    private BigDecimal valorM2;
    private BigDecimal totalGeral;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String local;
    private LocalDateTime dataCriacao;
    private boolean ativo;
    private boolean emAndamento;
    private boolean concluida;
    private String usuarioCriadorNome;

    public ObraResponseDTO() {}

    public ObraResponseDTO(String cei, String nome, String construtoraNome, Long construtoraId, BigDecimal valorM2, 
                          BigDecimal totalGeral, LocalDate dataInicio, LocalDate dataFim, 
                          String local, LocalDateTime dataCriacao, boolean ativo, 
                          boolean emAndamento, boolean concluida, String usuarioCriadorNome) {
        this.cei = cei;
        this.nome = nome;
        this.construtoraNome = construtoraNome;
        this.construtoraId = construtoraId;
        this.valorM2 = valorM2;
        this.totalGeral = totalGeral;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.emAndamento = emAndamento;
        this.concluida = concluida;
        this.usuarioCriadorNome = usuarioCriadorNome;
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

    public String getConstrutoraNome() {
        return construtoraNome;
    }

    public void setConstrutoraNome(String construtoraNome) {
        this.construtoraNome = construtoraNome;
    }

    public Long getConstrutoraId() {
        return construtoraId;
    }

    public void setConstrutoraId(Long construtoraId) {
        this.construtoraId = construtoraId;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public String getUsuarioCriadorNome() {
        return usuarioCriadorNome;
    }

    public void setUsuarioCriadorNome(String usuarioCriadorNome) {
        this.usuarioCriadorNome = usuarioCriadorNome;
    }
} 