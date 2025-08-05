package com.buildflow.equipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeModel, Long> {

    Optional<EquipeModel> findByIdAndAtivoTrue(Long id);
    
    List<EquipeModel> findByAtivoTrue();
    
    List<EquipeModel> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    
    @Query("SELECT e FROM EquipeModel e WHERE e.ativo = true AND SIZE(e.usuarios) > 0")
    List<EquipeModel> findEquipesComUsuarios();
    
    @Query("SELECT e FROM EquipeModel e WHERE e.ativo = true AND SIZE(e.usuarios) = 0")
    List<EquipeModel> findEquipesVazias();
    
    @Query("SELECT e FROM EquipeModel e JOIN e.usuarios u WHERE u.id = :usuarioId AND e.ativo = true")
    List<EquipeModel> findEquipesByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    boolean existsByNomeAndAtivoTrue(String nome);
    
    @Query("SELECT COUNT(e) FROM EquipeModel e WHERE e.ativo = true")
    long countEquipesAtivas();
}
