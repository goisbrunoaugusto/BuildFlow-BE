package com.buildflow.atividade.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtividadeCreateDTO {

    @NotNull(message = "m² executado é obrigatório")
    @DecimalMin(value = "0.01", message = "m² executado deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "m² executado deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal m2Executado;

    @NotNull(message = "m³ executado de argamassa é obrigatório")
    @DecimalMin(value = "0.0", message = "m³ executado de argamassa deve ser maior ou igual a zero")
    @Digits(integer = 10, fraction = 2, message = "m³ executado de argamassa deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal m3ExecutadoArgamassa;

    private LocalDateTime dataRealizada;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "ID da equipe é obrigatório")
    private Long equipeId;

    @NotNull(message = "ID do pavimento é obrigatório")
    private Long pavimentoId;

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    public Long getPavimentoId() {
        return pavimentoId;
    }

    public void setPavimentoId(Long pavimentoId) {
        this.pavimentoId = pavimentoId;
    }
} 