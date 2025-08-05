package com.buildflow.obra;

import com.buildflow.obra.dto.ObraCreateDTO;
import com.buildflow.obra.dto.ObraResponseDTO;
import com.buildflow.obra.dto.ObraUpdateDTO;
import com.buildflow.usuario.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/obras")
@CrossOrigin(origins = "*")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @GetMapping
    public ResponseEntity<List<ObraResponseDTO>> getAllObras() {
        List<ObraResponseDTO> obras = obraService.findAllActive();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/{cei}")
    public ResponseEntity<ObraResponseDTO> getObraByCei(@PathVariable String cei) {
        Optional<ObraResponseDTO> obra = obraService.findByCei(cei);
        return obra.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/construtora/{construtora}")
    public ResponseEntity<List<ObraResponseDTO>> getObrasByConstrutora(@PathVariable String construtora) {
        List<ObraResponseDTO> obras = obraService.findByConstrutora(construtora);
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/status/em-andamento")
    public ResponseEntity<List<ObraResponseDTO>> getObrasEmAndamento() {
        List<ObraResponseDTO> obras = obraService.findObrasEmAndamento();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/status/concluidas")
    public ResponseEntity<List<ObraResponseDTO>> getObrasConcluidas() {
        List<ObraResponseDTO> obras = obraService.findObrasConcluidas();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/count/ativas")
    public ResponseEntity<Long> countActiveObras() {
        long count = obraService.countActiveObras();
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<ObraResponseDTO> createObra(@Valid @RequestBody ObraCreateDTO createDTO) {
        try {
            // TODO: Get current user from security context
            UsuarioModel currentUser = getCurrentUser();
            ObraResponseDTO createdObra = obraService.create(createDTO, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdObra);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cei}")
    public ResponseEntity<ObraResponseDTO> updateObra(@PathVariable String cei, 
                                                     @Valid @RequestBody ObraUpdateDTO updateDTO) {
        Optional<ObraResponseDTO> updatedObra = obraService.update(cei, updateDTO);
        return updatedObra.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cei}")
    public ResponseEntity<Void> deleteObra(@PathVariable String cei) {
        boolean deleted = obraService.delete(cei);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // TODO: Implement proper user authentication
    private UsuarioModel getCurrentUser() {
        // Placeholder - should be implemented with proper security context
        UsuarioModel user = new UsuarioModel();
        user.setId(1L);
        user.setNome("Current");
        user.setSobrenome("User");
        return user;
    }
}
