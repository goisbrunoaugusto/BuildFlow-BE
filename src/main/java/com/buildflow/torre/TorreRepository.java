package com.buildflow.torre;

import com.buildflow.torre.enums.TorreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TorreRepository extends JpaRepository<TorreModel, Long> {

    Optional<TorreModel> findByIdAndAtivoTrue(Long id);
    
    List<TorreModel> findByAtivoTrue();
    
    List<TorreModel> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    
    List<TorreModel> findByStatusTorreAndAtivoTrue(TorreStatus statusTorre);
    
    List<TorreModel> findByObraCeiAndAtivoTrue(String ceiObra);
    
    List<TorreModel> findByDataInicioBetweenAndAtivoTrue(LocalDate dataInicio, LocalDate dataFim);
    
    @Query("SELECT t FROM TorreModel t WHERE t.ativo = true AND " +
           "(t.dataFim IS NULL OR t.dataFim >= :dataAtual)")
    List<TorreModel> findTorresEmAndamento(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT t FROM TorreModel t WHERE t.ativo = true AND " +
           "t.dataFim IS NOT NULL AND t.dataFim < :dataAtual")
    List<TorreModel> findTorresConcluidas(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT COUNT(t) FROM TorreModel t WHERE t.ativo = true")
    long countTorresAtivas();
    
    @Query("SELECT COUNT(t) FROM TorreModel t WHERE t.ativo = true AND t.statusTorre = :status")
    long countTorresPorStatus(@Param("status") TorreStatus status);
    
    @Query("SELECT COUNT(t) FROM TorreModel t WHERE t.ativo = true AND t.obra.cei = :ceiObra")
    long countTorresPorObra(@Param("ceiObra") String ceiObra);
}
