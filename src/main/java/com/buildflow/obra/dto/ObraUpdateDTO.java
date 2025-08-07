package com.buildflow.obra.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ObraUpdateDTO {

    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @Size(max = 255, message = "Nome da construtora principal deve ter no máximo 255 caracteres")
    private String construtora;

    @DecimalMin(value = "0.01", message = "Valor por m² deve ser maior que zero")
    @Digits(integer = 13, fraction = 2, message = "Valor por m² deve ter no máximo 13 dígitos inteiros e 2 decimais")
    private BigDecimal valorM2;

    @DecimalMin(value = "0.01", message = "Total geral deve ser maior que zero")
    @Digits(integer = 13, fraction = 2, message = "Total geral deve ter no máximo 13 dígitos inteiros e 2 decimais")
    private BigDecimal totalGeral;

    @FutureOrPresent(message = "Data de início deve ser hoje ou uma data futura")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Size(max = 50, message = "Local deve ter no máximo 50 caracteres")
    private String local;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConstrutora() {
        return construtora;
    }

    public void setConstrutora(String construtora) {
        this.construtora = construtora;
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
} 