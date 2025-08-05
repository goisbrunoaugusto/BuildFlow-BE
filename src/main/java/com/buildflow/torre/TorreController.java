package com.buildflow.torre;

import com.buildflow.torre.dto.TorreCreateDTO;
import com.buildflow.torre.dto.TorreResponseDTO;
import com.buildflow.torre.dto.TorreUpdateDTO;
import com.buildflow.torre.enums.TorreStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torres")
@CrossOrigin(origins = "*")
public class TorreController {

    @Autowired
    private TorreService torreService;

    @GetMapping
    public ResponseEntity<List<TorreResponseDTO>> findAllActive() {
        List<TorreResponseDTO> torres = torreService.findAllActive();
        return ResponseEntity.ok(torres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TorreResponseDTO> findById(@PathVariable Long id) {
        return torreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<TorreResponseDTO>> findByNome(@RequestParam String nome) {
        List<TorreResponseDTO> torres = torreService.findByNome(nome);
        return ResponseEntity.ok(torres);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TorreResponseDTO>> findByStatus(@PathVariable TorreStatus status) {
        List<TorreResponseDTO> torres = torreService.findByStatus(status);
        return ResponseEntity.ok(torres);
    }

    @GetMapping("/obra/{ceiObra}")
    public ResponseEntity<List<TorreResponseDTO>> findByObra(@PathVariable String ceiObra) {
        List<TorreResponseDTO> torres = torreService.findByObra(ceiObra);
        return ResponseEntity.ok(torres);
    }

    @GetMapping("/em-andamento")
    public ResponseEntity<List<TorreResponseDTO>> findTorresEmAndamento() {
        List<TorreResponseDTO> torres = torreService.findTorresEmAndamento();
        return ResponseEntity.ok(torres);
    }

    @GetMapping("/concluidas")
    public ResponseEntity<List<TorreResponseDTO>> findTorresConcluidas() {
        List<TorreResponseDTO> torres = torreService.findTorresConcluidas();
        return ResponseEntity.ok(torres);
    }

    @PostMapping
    public ResponseEntity<TorreResponseDTO> create(@Valid @RequestBody TorreCreateDTO createDTO) {
        try {
            TorreResponseDTO torre = torreService.create(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(torre);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TorreResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TorreUpdateDTO updateDTO) {
        try {
            return torreService.update(id, updateDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = torreService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count/ativas")
    public ResponseEntity<Long> countActiveTorres() {
        long count = torreService.countActiveTorres();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countTorresPorStatus(@PathVariable TorreStatus status) {
        long count = torreService.countTorresPorStatus(status);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/obra/{ceiObra}")
    public ResponseEntity<Long> countTorresPorObra(@PathVariable String ceiObra) {
        long count = torreService.countTorresPorObra(ceiObra);
        return ResponseEntity.ok(count);
    }
}
