package com.buildflow.construtora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConstrutoraRepository extends JpaRepository<ConstrutoraModel, Long> {

    Optional<ConstrutoraModel> findById(Long id);
    
    List<ConstrutoraModel> findAll();
    
    Optional<ConstrutoraModel> findByNomeIgnoreCase(String nome);
    
    List<ConstrutoraModel> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);
    
    @Query("SELECT c FROM ConstrutoraModel c ORDER BY c.nome")
    List<ConstrutoraModel> findAllOrderedByName();
    
    @Query("SELECT c FROM ConstrutoraModel c WHERE " +
           "c.nome LIKE %:termo% ORDER BY c.nome")
    List<ConstrutoraModel> searchByNome(@Param("termo") String termo);
    
    @Query("SELECT COUNT(c) FROM ConstrutoraModel c")
    long countConstrutoras();
    
    @Query("SELECT c FROM ConstrutoraModel c WHERE " +
           "SIZE(c.obras) > 0 ORDER BY SIZE(c.obras) DESC")
    List<ConstrutoraModel> findConstrutorasComObras();
} 