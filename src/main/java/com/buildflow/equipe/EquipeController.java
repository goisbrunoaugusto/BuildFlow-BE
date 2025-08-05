package com.buildflow.equipe;

import com.buildflow.equipe.dto.EquipeCreateDTO;
import com.buildflow.equipe.dto.EquipeResponseDTO;
import com.buildflow.equipe.dto.EquipeUpdateDTO;
import com.buildflow.usuario.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/equipes")
@CrossOrigin(origins = "*")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public ResponseEntity<List<EquipeResponseDTO>> getAllEquipes() {
        List<EquipeResponseDTO> equipes = equipeService.findAllActive();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> getEquipeById(@PathVariable Long id) {
        Optional<EquipeResponseDTO> equipe = equipeService.findById(id);
        return equipe.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EquipeResponseDTO>> getEquipesByNome(@PathVariable String nome) {
        List<EquipeResponseDTO> equipes = equipeService.findByNome(nome);
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/status/com-usuarios")
    public ResponseEntity<List<EquipeResponseDTO>> getEquipesComUsuarios() {
        List<EquipeResponseDTO> equipes = equipeService.findEquipesComUsuarios();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/status/vazias")
    public ResponseEntity<List<EquipeResponseDTO>> getEquipesVazias() {
        List<EquipeResponseDTO> equipes = equipeService.findEquipesVazias();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EquipeResponseDTO>> getEquipesByUsuarioId(@PathVariable Long usuarioId) {
        List<EquipeResponseDTO> equipes = equipeService.findEquipesByUsuarioId(usuarioId);
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/count/ativas")
    public ResponseEntity<Long> countActiveEquipes() {
        long count = equipeService.countActiveEquipes();
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<EquipeResponseDTO> createEquipe(@Valid @RequestBody EquipeCreateDTO createDTO) {
        try {
            EquipeResponseDTO createdEquipe = equipeService.create(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> updateEquipe(@PathVariable Long id, 
                                                         @Valid @RequestBody EquipeUpdateDTO updateDTO) {
        try {
            Optional<EquipeResponseDTO> updatedEquipe = equipeService.update(id, updateDTO);
            return updatedEquipe.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        boolean deleted = equipeService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/usuarios/{usuarioId}")
        public ResponseEntity<Void> adicionarUsuario(@PathVariable Long id, @PathVariable Long usuarioId) {
        // TODO: Get user from user service
        UsuarioModel usuario = getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        boolean added = equipeService.adicionarUsuario(id, usuario);
        return added ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/usuarios/{usuarioId}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id, @PathVariable Long usuarioId) {
        // TODO: Get user from user service
        UsuarioModel usuario = getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        boolean removed = equipeService.removerUsuario(id, usuario);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // TODO: Implement proper user service integration
    private UsuarioModel getUsuarioById(Long usuarioId) {
        // Placeholder - should be implemented with proper user service
        UsuarioModel user = new UsuarioModel();
        user.setId(usuarioId);
        user.setNome("User");
        user.setSobrenome("Name");
        return user;
    }
}
