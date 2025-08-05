package com.buildflow.usuario;

import com.buildflow.usuario.dto.UsuarioCreateDTO;
import com.buildflow.usuario.dto.UsuarioResponseDTO;
import com.buildflow.usuario.dto.UsuarioUpdateDTO;
import com.buildflow.usuario.enums.UsuarioFuncao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAllActive() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAllActive();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> findByEmail(@PathVariable String email) {
        return usuarioService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/funcao/{funcao}")
    public ResponseEntity<List<UsuarioResponseDTO>> findByFuncao(@PathVariable UsuarioFuncao funcao) {
        List<UsuarioResponseDTO> usuarios = usuarioService.findByFuncao(funcao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<UsuarioResponseDTO>> findByTermoBusca(@RequestParam String termo) {
        List<UsuarioResponseDTO> usuarios = usuarioService.findByTermoBusca(termo);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        try {
            UsuarioResponseDTO usuario = usuarioService.create(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO updateDTO) {
        try {
            return usuarioService.update(id, updateDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = usuarioService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count/ativos")
    public ResponseEntity<Long> countActiveUsuarios() {
        long count = usuarioService.countActiveUsuarios();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/funcao/{funcao}")
    public ResponseEntity<Long> countUsuariosPorFuncao(@PathVariable UsuarioFuncao funcao) {
        long count = usuarioService.countUsuariosPorFuncao(funcao);
        return ResponseEntity.ok(count);
    }
}
