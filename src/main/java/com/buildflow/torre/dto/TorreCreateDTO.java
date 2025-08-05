package com.buildflow.torre.dto;

import com.buildflow.torre.enums.TorreStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TorreCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(message = "Status da torre é obrigatório")
    private TorreStatus statusTorre;

    @NotNull(message = "Número de pavimentos é obrigatório")
    @Min(value = 1, message = "Número de pavimentos deve ser pelo menos 1")
    @Max(value = 200, message = "Número de pavimentos deve ser no máximo 200")
    private Integer pavimentos;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @NotBlank(message = "CEI da obra é obrigatório")
    @Size(max = 50, message = "CEI deve ter no máximo 50 caracteres")
    private String ceiObra;

    // Getters and Setters
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

    public String getCeiObra() {
        return ceiObra;
    }

    public void setCeiObra(String ceiObra) {
        this.ceiObra = ceiObra;
    }
} 