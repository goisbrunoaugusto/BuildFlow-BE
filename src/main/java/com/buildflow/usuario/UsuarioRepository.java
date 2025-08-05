package com.buildflow.usuario;

import com.buildflow.usuario.enums.UsuarioFuncao;
import com.buildflow.usuario.enums.UsuarioStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmailAndAtivoTrue(String email);
    
    List<UsuarioModel> findByAtivoTrue();
    
    List<UsuarioModel> findByFuncaoAndAtivoTrue(UsuarioFuncao funcao);
    
    List<UsuarioModel> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    
    List<UsuarioModel> findBySobrenomeContainingIgnoreCaseAndAtivoTrue(String sobrenome);
    
    @Query("SELECT u FROM UsuarioModel u WHERE u.ativo = true AND " +
           "(u.nome LIKE %:termo% OR u.sobrenome LIKE %:termo% OR u.email LIKE %:termo%)")
    List<UsuarioModel> findByTermoBusca(@Param("termo") String termo);
    
    boolean existsByEmailAndAtivoTrue(String email);
    
    @Query("SELECT COUNT(u) FROM UsuarioModel u WHERE u.ativo = true")
    long countUsuariosAtivos();
    
    @Query("SELECT COUNT(u) FROM UsuarioModel u WHERE u.ativo = true AND u.funcao = :funcao")
    long countUsuariosPorFuncao(@Param("funcao") UsuarioFuncao funcao);
}
