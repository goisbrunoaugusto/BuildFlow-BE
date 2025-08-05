package com.buildflow.atividade.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtividadeUpdateDTO {

    @DecimalMin(value = "0.01", message = "m² executado deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "m² executado deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal m2Executado;

    @DecimalMin(value = "0.0", message = "m³ executado de argamassa deve ser maior ou igual a zero")
    @Digits(integer = 10, fraction = 2, message = "m³ executado de argamassa deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal m3ExecutadoArgamassa;

    private LocalDateTime dataRealizada;

    private Boolean ativo;

    public BigDecimal getM2Executado() {
        return m2Executado;
    }

    public void setM2Executado(BigDecimal m2Executado) {
        this.m2Executado = m2Executado;
    }

    public BigDecimal getM3ExecutadoArgamassa() {
        return m3ExecutadoArgamassa;
    }

    public void setM3ExecutadoArgamassa(BigDecimal m3ExecutadoArgamassa) {
        this.m3ExecutadoArgamassa = m3ExecutadoArgamassa;
    }

    public LocalDateTime getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDateTime dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
} 