package com.buildflow.construtora;

import com.buildflow.construtora.dto.ConstrutoraCreateDTO;
import com.buildflow.construtora.dto.ConstrutoraResponseDTO;
import com.buildflow.construtora.dto.ConstrutoraUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/construtoras")
@CrossOrigin(origins = "*")
public class ConstrutoraController {

    @Autowired
    private ConstrutoraService construtoraService;

    @GetMapping
    public ResponseEntity<List<ConstrutoraResponseDTO>> getAllConstrutoras() {
        List<ConstrutoraResponseDTO> construtoras = construtoraService.findAll();
        return ResponseEntity.ok(construtoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstrutoraResponseDTO> getConstrutoraById(@PathVariable Long id) {
        Optional<ConstrutoraResponseDTO> construtora = construtoraService.findById(id);
        return construtora.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ConstrutoraResponseDTO> getConstrutoraByNome(@PathVariable String nome) {
        Optional<ConstrutoraResponseDTO> construtora = construtoraService.findByNome(nome);
        return construtora.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConstrutoraResponseDTO>> searchConstrutoras(@RequestParam String termo) {
        List<ConstrutoraResponseDTO> construtoras = construtoraService.searchByNome(termo);
        return ResponseEntity.ok(construtoras);
    }

    @GetMapping("/com-obras")
    public ResponseEntity<List<ConstrutoraResponseDTO>> getConstrutorasComObras() {
        List<ConstrutoraResponseDTO> construtoras = construtoraService.findConstrutorasComObras();
        return ResponseEntity.ok(construtoras);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countConstrutoras() {
        long count = construtoraService.countConstrutoras();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<ConstrutoraService.ConstrutoraStatsDTO> getEstatisticas() {
        ConstrutoraService.ConstrutoraStatsDTO estatisticas = construtoraService.getEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }

    @PostMapping
    public ResponseEntity<ConstrutoraResponseDTO> createConstrutora(@Valid @RequestBody ConstrutoraCreateDTO createDTO) {
        try {
            ConstrutoraResponseDTO createdConstrutora = construtoraService.create(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdConstrutora);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstrutoraResponseDTO> updateConstrutora(@PathVariable Long id, 
                                                                   @Valid @RequestBody ConstrutoraUpdateDTO updateDTO) {
        try {
            Optional<ConstrutoraResponseDTO> updatedConstrutora = construtoraService.update(id, updateDTO);
            return updatedConstrutora.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConstrutora(@PathVariable Long id) {
        try {
            boolean deleted = construtoraService.delete(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 