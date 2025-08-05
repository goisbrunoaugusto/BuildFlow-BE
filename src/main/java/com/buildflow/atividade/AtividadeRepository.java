package com.buildflow.atividade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeModel, Long> {

    List<AtividadeModel> findByUsuarioId(Long usuarioId);
    
    List<AtividadeModel> findByEquipeId(Long equipeId);
    
    List<AtividadeModel> findByPavimentoId(Long pavimentoId);
    
    List<AtividadeModel> findByPavimentoTorreId(Long torreId);
    
    List<AtividadeModel> findByPavimentoTorreObraCei(String ceiObra);
    
    List<AtividadeModel> findByDataRealizadaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    List<AtividadeModel> findByDataRealizadaDate(LocalDate data);
    
    @Query("SELECT a FROM AtividadeModel a WHERE " +
           "DATE(a.dataRealizada) = :dataAtual")
    List<AtividadeModel> findAtividadesHoje(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT a FROM AtividadeModel a WHERE " +
           "a.m2Executado >= :m2Minimo")
    List<AtividadeModel> findAtividadesComM2Minimo(@Param("m2Minimo") BigDecimal m2Minimo);
    
    @Query("SELECT a FROM AtividadeModel a WHERE " +
           "a.m3ExecutadoArgamassa >= :m3Minimo")
    List<AtividadeModel> findAtividadesComM3Minimo(@Param("m3Minimo") BigDecimal m3Minimo);
    
    @Query("SELECT COUNT(a) FROM AtividadeModel a")
    long countAtividadesAtivas();
    
    @Query("SELECT COUNT(a) FROM AtividadeModel a WHERE a.usuario.id = :usuarioId")
    long countAtividadesPorUsuario(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(a) FROM AtividadeModel a WHERE a.equipe.id = :equipeId")
    long countAtividadesPorEquipe(@Param("equipeId") Long equipeId);
    
    @Query("SELECT COUNT(a) FROM AtividadeModel a WHERE a.pavimento.id = :pavimentoId")
    long countAtividadesPorPavimento(@Param("pavimentoId") Long pavimentoId);
    
    @Query("SELECT SUM(a.m2Executado) FROM AtividadeModel a WHERE a.usuario.id = :usuarioId")
    BigDecimal sumM2ExecutadoPorUsuario(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT SUM(a.m2Executado) FROM AtividadeModel a WHERE a.equipe.id = :equipeId")
    BigDecimal sumM2ExecutadoPorEquipe(@Param("equipeId") Long equipeId);
    
    @Query("SELECT SUM(a.m2Executado) FROM AtividadeModel a WHERE a.pavimento.id = :pavimentoId")
    BigDecimal sumM2ExecutadoPorPavimento(@Param("pavimentoId") Long pavimentoId);
    
    @Query("SELECT SUM(a.m3ExecutadoArgamassa) FROM AtividadeModel a WHERE a.usuario.id = :usuarioId")
    BigDecimal sumM3ExecutadoPorUsuario(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT SUM(a.m3ExecutadoArgamassa) FROM AtividadeModel a WHERE a.equipe.id = :equipeId")
    BigDecimal sumM3ExecutadoPorEquipe(@Param("equipeId") Long equipeId);
    
    @Query("SELECT SUM(a.m3ExecutadoArgamassa) FROM AtividadeModel a WHERE a.pavimento.id = :pavimentoId")
    BigDecimal sumM3ExecutadoPorPavimento(@Param("pavimentoId") Long pavimentoId);
}
