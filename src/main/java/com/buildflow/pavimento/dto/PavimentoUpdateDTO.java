package com.buildflow.pavimento.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PavimentoUpdateDTO {

    @DecimalMin(value = "0.01", message = "Área do pavimento deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Área do pavimento deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal areaPavimento;

    @DecimalMin(value = "0.0", message = "m³ total de argamassa deve ser maior ou igual a zero")
    @Digits(integer = 10, fraction = 2, message = "m³ total de argamassa deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal m3TotalArgamassa;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Size(max = 1000, message = "Observações deve ter no máximo 1000 caracteres")
    private String observacoes;

    private Boolean ativo;

    // Getters and Setters
    public BigDecimal getAreaPavimento() {
        return areaPavimento;
    }

    public void setAreaPavimento(BigDecimal areaPavimento) {
        this.areaPavimento = areaPavimento;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
} 