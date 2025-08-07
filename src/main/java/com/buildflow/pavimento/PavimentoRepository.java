package com.buildflow.pavimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PavimentoRepository extends JpaRepository<PavimentoModel, Long> {

    Optional<PavimentoModel> findByIdAndAtivoTrue(Long id);
    
    List<PavimentoModel> findByAtivoTrue();
    
    List<PavimentoModel> findByTorreIdAndAtivoTrue(Long torreId);
    
    List<PavimentoModel> findByTorreObraCeiAndAtivoTrue(String ceiObra);
    
    List<PavimentoModel> findByDataInicioBetweenAndAtivoTrue(LocalDate dataInicio, LocalDate dataFim);
    
    List<PavimentoModel> findByDataFimBetweenAndAtivoTrue(LocalDate dataInicio, LocalDate dataFim);
    
    @Query("SELECT p FROM PavimentoModel p WHERE p.ativo = true AND " +
           "(p.dataFim IS NULL OR p.dataFim >= :dataAtual)")
    List<PavimentoModel> findPavimentosEmAndamento(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT p FROM PavimentoModel p WHERE p.ativo = true AND " +
           "p.dataFim IS NOT NULL AND p.dataFim < :dataAtual")
    List<PavimentoModel> findPavimentosConcluidos(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT p FROM PavimentoModel p WHERE p.ativo = true AND " +
           "p.areaExecutada >= p.areaPavimento")
    List<PavimentoModel> findPavimentosCompletos();
    
    @Query("SELECT p FROM PavimentoModel p WHERE p.ativo = true AND " +
           "p.areaExecutada < p.areaPavimento")
    List<PavimentoModel> findPavimentosIncompletos();
    
    @Query("SELECT p FROM PavimentoModel p WHERE p.ativo = true AND " +
           "p.areaExecutada >= :areaMinima")
    List<PavimentoModel> findPavimentosComAreaExecutadaMinima(@Param("areaMinima") BigDecimal areaMinima);
    
    @Query("SELECT COUNT(p) FROM PavimentoModel p WHERE p.ativo = true")
    long countPavimentosAtivos();
    
    @Query("SELECT COUNT(p) FROM PavimentoModel p WHERE p.ativo = true AND p.torre.id = :torreId")
    long countPavimentosPorTorre(@Param("torreId") Long torreId);
    
    @Query("SELECT COUNT(p) FROM PavimentoModel p WHERE p.ativo = true AND p.torre.obra.cei = :ceiObra")
    long countPavimentosPorObra(@Param("ceiObra") String ceiObra);
    
    @Query("SELECT SUM(p.areaExecutada) FROM PavimentoModel p WHERE p.ativo = true AND p.torre.id = :torreId")
    BigDecimal sumAreaExecutadaPorTorre(@Param("torreId") Long torreId);
    
    @Query("SELECT SUM(p.areaExecutada) FROM PavimentoModel p WHERE p.ativo = true AND p.torre.obra.cei = :ceiObra")
    BigDecimal sumAreaExecutadaPorObra(@Param("ceiObra") String ceiObra);
}
