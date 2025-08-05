package com.buildflow.atividade;

import com.buildflow.equipe.EquipeModel;
import com.buildflow.pavimento.PavimentoModel;
import com.buildflow.usuario.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.RoundingMode;

@Entity
@Table(name = "db_atividades")
public class AtividadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "m2_executado", nullable = false, precision = 10, scale = 2)
    private BigDecimal m2Executado;

    @Column(name = "m3_executado_argamassa", nullable = false, precision = 10, scale = 2)
    private BigDecimal m3ExecutadoArgamassa;

    @Column(name = "data_realizada", nullable = false)
    private LocalDateTime dataRealizada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id", nullable = false)
    @JsonIgnore
    private EquipeModel equipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pavimento_id", nullable = false)
    @JsonIgnore
    private PavimentoModel pavimento;

    public AtividadeModel() {
        this.dataRealizada = LocalDateTime.now();
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

    public void setDataRealizada(LocalDateTime dataAtividade) {
        this.dataRealizada = dataAtividade;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public EquipeModel getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeModel equipe) {
        this.equipe = equipe;
    }

    public PavimentoModel getPavimento() {
        return pavimento;
    }

    public void setPavimento(PavimentoModel pavimento) {
        this.pavimento = pavimento;
    }

    public boolean isHoje() {
        return dataRealizada != null && dataRealizada.toLocalDate().equals(LocalDate.now());
    }

    public boolean isEstaSemana() {
        if (dataRealizada == null) return false;
        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.minusDays(hoje.getDayOfWeek().getValue() - 1);
        LocalDate fimSemana = inicioSemana.plusDays(6);
        return !dataRealizada.toLocalDate().isBefore(inicioSemana) && !dataRealizada.toLocalDate().isAfter(fimSemana);
    }

    public boolean isEsteMes() {
        if (dataRealizada == null) return false;
        LocalDate hoje = LocalDate.now();
        return dataRealizada.toLocalDate().getYear() == hoje.getYear() && dataRealizada.toLocalDate().getMonth() == hoje.getMonth();
    }

    public BigDecimal getRendimentoM2PorM3() {
        if (m3ExecutadoArgamassa == null || m3ExecutadoArgamassa.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return m2Executado.divide(m3ExecutadoArgamassa, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal getEspessuraArgamassa(){
        if(m2Executado == null || m2Executado.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        return m3ExecutadoArgamassa.divide(m2Executado, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

    public String getEmailUsuario() {
        return usuario != null ? usuario.getEmail() : null;
    }

    public String getNomeEquipe() {
        return equipe != null ? equipe.getNome() : null;
    }
}
