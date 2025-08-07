package com.buildflow.construtora;

import com.buildflow.construtora.dto.ConstrutoraCreateDTO;
import com.buildflow.construtora.dto.ConstrutoraResponseDTO;
import com.buildflow.construtora.dto.ConstrutoraUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConstrutoraService {

    @Autowired
    private ConstrutoraRepository construtoraRepository;

    public List<ConstrutoraResponseDTO> findAll() {
        return construtoraRepository.findAllOrderedByName()
                .stream()
                .map(ConstrutoraResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<ConstrutoraResponseDTO> findById(Long id) {
        return construtoraRepository.findById(id)
                .map(ConstrutoraResponseDTO::new);
    }

    public Optional<ConstrutoraResponseDTO> findByNome(String nome) {
        return construtoraRepository.findByNomeIgnoreCase(nome)
                .map(ConstrutoraResponseDTO::new);
    }

    public List<ConstrutoraResponseDTO> searchByNome(String termo) {
        return construtoraRepository.searchByNome(termo)
                .stream()
                .map(ConstrutoraResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<ConstrutoraResponseDTO> findConstrutorasComObras() {
        return construtoraRepository.findConstrutorasComObras()
                .stream()
                .map(ConstrutoraResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ConstrutoraResponseDTO create(ConstrutoraCreateDTO createDTO) {
        // Verificar se já existe uma construtora com o mesmo nome
        if (construtoraRepository.existsByNomeIgnoreCase(createDTO.getNome())) {
            throw new IllegalArgumentException("Já existe uma construtora com o nome: " + createDTO.getNome());
        }

        ConstrutoraModel construtora = new ConstrutoraModel();
        construtora.setNome(createDTO.getNome());
        construtora.setCnpj(createDTO.getCnpj());
        construtora.setEndereco(createDTO.getEndereco());
        construtora.setTelefone(createDTO.getTelefone());
        construtora.setEmail(createDTO.getEmail());
        construtora.setWebsite(createDTO.getWebsite());

        ConstrutoraModel savedConstrutora = construtoraRepository.save(construtora);
        return new ConstrutoraResponseDTO(savedConstrutora);
    }

    public Optional<ConstrutoraResponseDTO> update(Long id, ConstrutoraUpdateDTO updateDTO) {
        return construtoraRepository.findById(id)
                .map(construtora -> {
                    // Se o nome está sendo alterado, verificar se já existe
                    if (updateDTO.getNome() != null && !updateDTO.getNome().equalsIgnoreCase(construtora.getNome())) {
                        if (construtoraRepository.existsByNomeIgnoreCase(updateDTO.getNome())) {
                            throw new IllegalArgumentException("Já existe uma construtora com o nome: " + updateDTO.getNome());
                        }
                        construtora.setNome(updateDTO.getNome());
                    }

                    if (updateDTO.getCnpj() != null) {
                        construtora.setCnpj(updateDTO.getCnpj());
                    }
                    if (updateDTO.getEndereco() != null) {
                        construtora.setEndereco(updateDTO.getEndereco());
                    }
                    if (updateDTO.getTelefone() != null) {
                        construtora.setTelefone(updateDTO.getTelefone());
                    }
                    if (updateDTO.getEmail() != null) {
                        construtora.setEmail(updateDTO.getEmail());
                    }
                    if (updateDTO.getWebsite() != null) {
                        construtora.setWebsite(updateDTO.getWebsite());
                    }
                    ConstrutoraModel savedConstrutora = construtoraRepository.save(construtora);
                    return new ConstrutoraResponseDTO(savedConstrutora);
                });
    }

    public boolean delete(Long id) {
        return construtoraRepository.findById(id)
                .map(construtora -> {
                    // Verificar se a construtora tem obras ativas
                    if (construtora.getQuantidadeObrasAtivas() > 0) {
                        throw new IllegalStateException("Não é possível excluir uma construtora que possui obras ativas");
                    }
                    
                    construtoraRepository.delete(construtora);
                    return true;
                })
                .orElse(false);
    }

    public long countConstrutoras() {
        return construtoraRepository.countConstrutoras();
    }

    /**
     * Busca ou cria uma construtora pelo nome (útil para integração com obras)
     */
    public ConstrutoraModel findOrCreateByNome(String nome) {
        return construtoraRepository.findByNomeIgnoreCase(nome)
                .orElseGet(() -> {
                    ConstrutoraModel novaConstrutora = new ConstrutoraModel(nome);
                    return construtoraRepository.save(novaConstrutora);
                });
    }

    /**
     * Valida se uma construtora existe
     */
    public boolean isValidConstrutora(Long id) {
        return construtoraRepository.findById(id).isPresent();
    }

    /**
     * Retorna estatísticas das construtoras
     */
    public ConstrutoraStatsDTO getEstatisticas() {
        long totalConstrutoras = countConstrutoras();
        List<ConstrutoraResponseDTO> construtorasComObras = findConstrutorasComObras();
        
        return new ConstrutoraStatsDTO(
                totalConstrutoras,
                construtorasComObras.size(),
                construtorasComObras.stream()
                        .mapToInt(ConstrutoraResponseDTO::getQuantidadeObrasAtivas)
                        .sum()
        );
    }

    // DTO interno para estatísticas
    public static class ConstrutoraStatsDTO {
        private final long totalConstrutoras;
        private final long construtorasComObras;
        private final long totalObrasAtivas;

        public ConstrutoraStatsDTO(long totalConstrutoras, long construtorasComObras, long totalObrasAtivas) {
            this.totalConstrutoras = totalConstrutoras;
            this.construtorasComObras = construtorasComObras;
            this.totalObrasAtivas = totalObrasAtivas;
        }

        public long getTotalConstrutoras() { return totalConstrutoras; }
        public long getConstrutorasComObras() { return construtorasComObras; }
        public long getTotalObrasAtivas() { return totalObrasAtivas; }
    }
} 