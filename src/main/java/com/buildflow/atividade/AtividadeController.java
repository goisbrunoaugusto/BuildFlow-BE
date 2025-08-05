package com.buildflow.atividade;

import com.buildflow.atividade.dto.AtividadeCreateDTO;
import com.buildflow.atividade.dto.AtividadeResponseDTO;
import com.buildflow.atividade.dto.AtividadeUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@CrossOrigin(origins = "*")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeResponseDTO>> findAllActive() {
        List<AtividadeResponseDTO> atividades = atividadeService.findAllActive();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> findById(@PathVariable Long id) {
        return atividadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByUsuario(@PathVariable Long usuarioId) {
        List<AtividadeResponseDTO> atividades = atividadeService.findByUsuario(usuarioId);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/equipe/{equipeId}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByEquipe(@PathVariable Long equipeId) {
        List<AtividadeResponseDTO> atividades = atividadeService.findByEquipe(equipeId);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/pavimento/{pavimentoId}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByPavimento(@PathVariable Long pavimentoId) {
        List<AtividadeResponseDTO> atividades = atividadeService.findByPavimento(pavimentoId);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/torre/{torreId}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByTorre(@PathVariable Long torreId) {
        List<AtividadeResponseDTO> atividades = atividadeService.findByTorre(torreId);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/obra/{ceiObra}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByObra(@PathVariable String ceiObra) {
        List<AtividadeResponseDTO> atividades = atividadeService.findByObra(ceiObra);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/hoje")
    public ResponseEntity<List<AtividadeResponseDTO>> findAtividadesHoje() {
        List<AtividadeResponseDTO> atividades = atividadeService.findAtividadesHoje();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<AtividadeResponseDTO>> findAtividadesPorPeriodo(
            @RequestParam LocalDateTime dataInicio, 
            @RequestParam LocalDateTime dataFim) {
        List<AtividadeResponseDTO> atividades = atividadeService.findAtividadesPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/m2-minimo")
    public ResponseEntity<List<AtividadeResponseDTO>> findAtividadesComM2Minimo(@RequestParam BigDecimal m2Minimo) {
        List<AtividadeResponseDTO> atividades = atividadeService.findAtividadesComM2Minimo(m2Minimo);
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/m3-minimo")
    public ResponseEntity<List<AtividadeResponseDTO>> findAtividadesComM3Minimo(@RequestParam BigDecimal m3Minimo) {
        List<AtividadeResponseDTO> atividades = atividadeService.findAtividadesComM3Minimo(m3Minimo);
        return ResponseEntity.ok(atividades);
    }

    @PostMapping
    public ResponseEntity<AtividadeResponseDTO> create(@Valid @RequestBody AtividadeCreateDTO createDTO) {
        try {
            AtividadeResponseDTO atividade = atividadeService.create(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(atividade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AtividadeUpdateDTO updateDTO) {
        try {
            return atividadeService.update(id, updateDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = atividadeService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count/ativas")
    public ResponseEntity<Long> countActiveAtividades() {
        long count = atividadeService.countActiveAtividades();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/usuario/{usuarioId}")
    public ResponseEntity<Long> countAtividadesPorUsuario(@PathVariable Long usuarioId) {
        long count = atividadeService.countAtividadesPorUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/equipe/{equipeId}")
    public ResponseEntity<Long> countAtividadesPorEquipe(@PathVariable Long equipeId) {
        long count = atividadeService.countAtividadesPorEquipe(equipeId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/pavimento/{pavimentoId}")
    public ResponseEntity<Long> countAtividadesPorPavimento(@PathVariable Long pavimentoId) {
        long count = atividadeService.countAtividadesPorPavimento(pavimentoId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/sum/m2-executado/usuario/{usuarioId}")
    public ResponseEntity<BigDecimal> sumM2ExecutadoPorUsuario(@PathVariable Long usuarioId) {
        BigDecimal sum = atividadeService.sumM2ExecutadoPorUsuario(usuarioId);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/sum/m2-executado/equipe/{equipeId}")
    public ResponseEntity<BigDecimal> sumM2ExecutadoPorEquipe(@PathVariable Long equipeId) {
        BigDecimal sum = atividadeService.sumM2ExecutadoPorEquipe(equipeId);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/sum/m2-executado/pavimento/{pavimentoId}")
    public ResponseEntity<BigDecimal> sumM2ExecutadoPorPavimento(@PathVariable Long pavimentoId) {
        BigDecimal sum = atividadeService.sumM2ExecutadoPorPavimento(pavimentoId);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/sum/m3-executado/usuario/{usuarioId}")
    public ResponseEntity<BigDecimal> sumM3ExecutadoPorUsuario(@PathVariable Long usuarioId) {
        BigDecimal sum = atividadeService.sumM3ExecutadoPorUsuario(usuarioId);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/sum/m3-executado/equipe/{equipeId}")
    public ResponseEntity<BigDecimal> sumM3ExecutadoPorEquipe(@PathVariable Long equipeId) {
        BigDecimal sum = atividadeService.sumM3ExecutadoPorEquipe(equipeId);
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/sum/m3-executado/pavimento/{pavimentoId}")
    public ResponseEntity<BigDecimal> sumM3ExecutadoPorPavimento(@PathVariable Long pavimentoId) {
        BigDecimal sum = atividadeService.sumM3ExecutadoPorPavimento(pavimentoId);
        return ResponseEntity.ok(sum);
    }
}
