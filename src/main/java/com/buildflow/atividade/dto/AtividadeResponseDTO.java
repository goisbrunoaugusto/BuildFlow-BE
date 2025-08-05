package com.buildflow.atividade.dto;

import com.buildflow.atividade.AtividadeModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtividadeResponseDTO {

    private Long id;
    private BigDecimal m2Executado;
    private BigDecimal m3ExecutadoArgamassa;
    private LocalDateTime dataRealizada;
    private Long usuarioId;
    private String emailUsuario;
    private String nomeUsuario;
    private Long equipeId;
    private String nomeEquipe;
    private Long pavimentoId;
    private Long torreId;
    private String nomeTorre;
    private String ceiObra;
    private String nomeObra;
    private BigDecimal rendimentoM2PorM3;
    private BigDecimal espessuraArgamassa;
    private boolean hoje;
    private boolean estaSemana;
    private boolean esteMes;

    public AtividadeResponseDTO() {}

    public AtividadeResponseDTO(AtividadeModel atividade) {
        this.id = atividade.getId();
        this.m2Executado = atividade.getM2Executado();
        this.m3ExecutadoArgamassa = atividade.getM3ExecutadoArgamassa();
        this.dataRealizada = atividade.getDataRealizada();
        this.usuarioId = atividade.getUsuario() != null ? atividade.getUsuario().getId() : null;
        this.emailUsuario = atividade.getEmailUsuario();
        this.nomeUsuario = atividade.getUsuario() != null ? atividade.getUsuario().getNomeCompleto() : null;
        this.equipeId = atividade.getEquipe() != null ? atividade.getEquipe().getId() : null;
        this.nomeEquipe = atividade.getNomeEquipe();
        this.pavimentoId = atividade.getPavimento() != null ? atividade.getPavimento().getId() : null;
        this.torreId = atividade.getPavimento() != null && atividade.getPavimento().getTorre() != null ? 
                      atividade.getPavimento().getTorre().getId() : null;
        this.nomeTorre = atividade.getPavimento() != null && atividade.getPavimento().getTorre() != null ? 
                        atividade.getPavimento().getTorre().getNome() : null;
        this.ceiObra = atividade.getPavimento() != null && atividade.getPavimento().getTorre() != null && 
                      atividade.getPavimento().getTorre().getObra() != null ? 
                      atividade.getPavimento().getTorre().getObra().getCei() : null;
        this.nomeObra = atividade.getPavimento() != null && atividade.getPavimento().getTorre() != null && 
                       atividade.getPavimento().getTorre().getObra() != null ? 
                       atividade.getPavimento().getTorre().getObra().getNome() : null;
        this.rendimentoM2PorM3 = atividade.getRendimentoM2PorM3();
        this.espessuraArgamassa = atividade.getEspessuraArgamassa();
        this.hoje = atividade.isHoje();
        this.estaSemana = atividade.isEstaSemana();
        this.esteMes = atividade.isEsteMes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public Long getPavimentoId() {
        return pavimentoId;
    }

    public void setPavimentoId(Long pavimentoId) {
        this.pavimentoId = pavimentoId;
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

    public BigDecimal getRendimentoM2PorM3() {
        return rendimentoM2PorM3;
    }

    public void setRendimentoM2PorM3(BigDecimal rendimentoM2PorM3) {
        this.rendimentoM2PorM3 = rendimentoM2PorM3;
    }

    public BigDecimal getEspessuraArgamassa() {
        return espessuraArgamassa;
    }

    public void setEspessuraArgamassa(BigDecimal espessuraArgamassa) {
        this.espessuraArgamassa = espessuraArgamassa;
    }

    public boolean isHoje() {
        return hoje;
    }

    public void setHoje(boolean hoje) {
        this.hoje = hoje;
    }

    public boolean isEstaSemana() {
        return estaSemana;
    }

    public void setEstaSemana(boolean estaSemana) {
        this.estaSemana = estaSemana;
    }

    public boolean isEsteMes() {
        return esteMes;
    }

    public void setEsteMes(boolean esteMes) {
        this.esteMes = esteMes;
    }
} 