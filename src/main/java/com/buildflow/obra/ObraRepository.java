package com.buildflow.obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ObraRepository extends JpaRepository<ObraModel, Long> {

    Optional<ObraModel> findByCeiAndAtivoTrue(String cei);
    
    List<ObraModel> findByAtivoTrue();
    
    List<ObraModel> findByConstrutoraContainingIgnoreCaseAndAtivoTrue(String construtora);
    
    List<ObraModel> findByDataInicioBetweenAndAtivoTrue(LocalDate dataInicio, LocalDate dataFim);
    
    // TODO: Criar enum para status da obra (?)
    @Query("SELECT o FROM ObraModel o WHERE o.ativo = true AND " +
           "(o.dataFim IS NULL OR o.dataFim >= :dataAtual)")
    List<ObraModel> findObrasEmAndamento(@Param("dataAtual") LocalDate dataAtual);
    
    @Query("SELECT o FROM ObraModel o WHERE o.ativo = true AND " +
           "o.dataFim IS NOT NULL AND o.dataFim < :dataAtual")
    List<ObraModel> findObrasConcluidas(@Param("dataAtual") LocalDate dataAtual);
    
    boolean existsByCeiAndAtivoTrue(String cei);
    
    @Query("SELECT COUNT(o) FROM ObraModel o WHERE o.ativo = true")
    long countObrasAtivas();
}
