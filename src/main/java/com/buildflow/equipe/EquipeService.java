package com.buildflow.equipe;

import com.buildflow.equipe.dto.EquipeCreateDTO;
import com.buildflow.equipe.dto.EquipeResponseDTO;
import com.buildflow.equipe.dto.EquipeUpdateDTO;
import com.buildflow.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public List<EquipeResponseDTO> findAllActive() {
        return equipeRepository.findByAtivoTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EquipeResponseDTO> findById(Long id) {
        return equipeRepository.findByIdAndAtivoTrue(id)
                .map(this::convertToResponseDTO);
    }

    public List<EquipeResponseDTO> findByNome(String nome) {
        return equipeRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EquipeResponseDTO> findEquipesComUsuarios() {
        return equipeRepository.findEquipesComUsuarios()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EquipeResponseDTO> findEquipesVazias() {
        return equipeRepository.findEquipesVazias()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EquipeResponseDTO> findEquipesByUsuarioId(Long usuarioId) {
        return equipeRepository.findEquipesByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EquipeResponseDTO create(EquipeCreateDTO createDTO) {
        if (equipeRepository.existsByNomeAndAtivoTrue(createDTO.getNome())) {
            throw new IllegalArgumentException("Já existe uma equipe ativa com o nome: " + createDTO.getNome());
        }

        EquipeModel equipe = new EquipeModel();
        equipe.setNome(createDTO.getNome());

        EquipeModel savedEquipe = equipeRepository.save(equipe);
        return convertToResponseDTO(savedEquipe);
    }

    public Optional<EquipeResponseDTO> update(Long id, EquipeUpdateDTO updateDTO) {
        return equipeRepository.findByIdAndAtivoTrue(id)
                .map(equipe -> {
                    if (updateDTO.getNome() != null) {
                        // Check if the new name already exists (excluding current team)
                        if (!equipe.getNome().equals(updateDTO.getNome()) && 
                            equipeRepository.existsByNomeAndAtivoTrue(updateDTO.getNome())) {
                            throw new IllegalArgumentException("Já existe uma equipe ativa com o nome: " + updateDTO.getNome());
                        }
                        equipe.setNome(updateDTO.getNome());
                    }

                    EquipeModel savedEquipe = equipeRepository.save(equipe);
                    return convertToResponseDTO(savedEquipe);
                });
    }

    public boolean delete(Long id) {
        return equipeRepository.findByIdAndAtivoTrue(id)
                .map(equipe -> {
                    equipe.setAtivo(false);
                    equipeRepository.save(equipe);
                    return true;
                })
                .orElse(false);
    }

    public boolean adicionarUsuario(Long equipeId, UsuarioModel usuario) {
        return equipeRepository.findByIdAndAtivoTrue(equipeId)
                .map(equipe -> {
                    boolean added = equipe.adicionarUsuario(usuario);
                    if (added) {
                        equipeRepository.save(equipe);
                    }
                    return added;
                })
                .orElse(false);
    }

    public boolean removerUsuario(Long equipeId, UsuarioModel usuario) {
        return equipeRepository.findByIdAndAtivoTrue(equipeId)
                .map(equipe -> {
                    boolean removed = equipe.removerUsuario(usuario);
                    if (removed) {
                        equipeRepository.save(equipe);
                    }
                    return removed;
                })
                .orElse(false);
    }

    public long countActiveEquipes() {
        return equipeRepository.countEquipesAtivas();
    }

    private EquipeResponseDTO convertToResponseDTO(EquipeModel equipe) {
        List<String> usuariosNomes = equipe.getUsuarios().stream()
                .map(UsuarioModel::getNomeCompleto)
                .collect(Collectors.toList());

        return new EquipeResponseDTO(
                equipe.getId(),
                equipe.getNome(),
                equipe.getDataCriacao(),
                equipe.isAtivo(),
                equipe.getQuantidadeUsuarios(),
                usuariosNomes
        );
    }
}
