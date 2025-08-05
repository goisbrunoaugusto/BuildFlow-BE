package com.buildflow.pavimento;

import com.buildflow.pavimento.dto.PavimentoCreateDTO;
import com.buildflow.pavimento.dto.PavimentoResponseDTO;
import com.buildflow.pavimento.dto.PavimentoUpdateDTO;
import com.buildflow.torre.TorreModel;
import com.buildflow.torre.TorreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PavimentoService {

    @Autowired
    private PavimentoRepository pavimentoRepository;

    @Autowired
    private TorreRepository torreRepository;

    public List<PavimentoResponseDTO> findAllActive() {
        return pavimentoRepository.findByAtivoTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PavimentoResponseDTO> findById(Long id) {
        return pavimentoRepository.findByIdAndAtivoTrue(id)
                .map(this::convertToResponseDTO);
    }

    public List<PavimentoResponseDTO> findByTorre(Long torreId) {
        return pavimentoRepository.findByTorreIdAndAtivoTrue(torreId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findByObra(String ceiObra) {
        return pavimentoRepository.findByTorreObraCeiAndAtivoTrue(ceiObra)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findPavimentosEmAndamento() {
        return pavimentoRepository.findPavimentosEmAndamento(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findPavimentosConcluidos() {
        return pavimentoRepository.findPavimentosConcluidos(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findPavimentosCompletos() {
        return pavimentoRepository.findPavimentosCompletos()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findPavimentosIncompletos() {
        return pavimentoRepository.findPavimentosIncompletos()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PavimentoResponseDTO> findPavimentosComAreaExecutadaMinima(BigDecimal areaMinima) {
        return pavimentoRepository.findPavimentosComAreaExecutadaMinima(areaMinima)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PavimentoResponseDTO create(PavimentoCreateDTO createDTO) {
        TorreModel torre = torreRepository.findByIdAndAtivoTrue(createDTO.getTorreId())
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada com o ID: " + createDTO.getTorreId()));

        PavimentoModel pavimento = new PavimentoModel();
        pavimento.setAreaPavimento(createDTO.getAreaPavimento());
        pavimento.setM3TotalArgamassa(createDTO.getM3TotalArgamassa());
        pavimento.setDataInicio(createDTO.getDataInicio());
        pavimento.setDataFim(createDTO.getDataFim());
        pavimento.setObservacoes(createDTO.getObservacoes());
        pavimento.setTorre(torre);

        PavimentoModel savedPavimento = pavimentoRepository.save(pavimento);
        return convertToResponseDTO(savedPavimento);
    }

    public Optional<PavimentoResponseDTO> update(Long id, PavimentoUpdateDTO updateDTO) {
        return pavimentoRepository.findByIdAndAtivoTrue(id)
                .map(pavimento -> {
                    if (updateDTO.getAreaPavimento() != null) {
                        pavimento.setAreaPavimento(updateDTO.getAreaPavimento());
                    }
                    if (updateDTO.getM3TotalArgamassa() != null) {
                        pavimento.setM3TotalArgamassa(updateDTO.getM3TotalArgamassa());
                    }
                    if (updateDTO.getDataInicio() != null) {
                        pavimento.setDataInicio(updateDTO.getDataInicio());
                    }
                    if (updateDTO.getDataFim() != null) {
                        pavimento.setDataFim(updateDTO.getDataFim());
                    }
                    if (updateDTO.getObservacoes() != null) {
                        pavimento.setObservacoes(updateDTO.getObservacoes());
                    }
                    if (updateDTO.getAtivo() != null) {
                        pavimento.setAtivo(updateDTO.getAtivo());
                    }

                    PavimentoModel savedPavimento = pavimentoRepository.save(pavimento);
                    return convertToResponseDTO(savedPavimento);
                });
    }

    public boolean delete(Long id) {
        return pavimentoRepository.findByIdAndAtivoTrue(id)
                .map(pavimento -> {
                    pavimento.setAtivo(false);
                    pavimentoRepository.save(pavimento);
                    return true;
                })
                .orElse(false);
    }

    public long countActivePavimentos() {
        return pavimentoRepository.countPavimentosAtivos();
    }

    public long countPavimentosPorTorre(Long torreId) {
        return pavimentoRepository.countPavimentosPorTorre(torreId);
    }

    public long countPavimentosPorObra(String ceiObra) {
        return pavimentoRepository.countPavimentosPorObra(ceiObra);
    }

    public BigDecimal sumAreaExecutadaPorTorre(Long torreId) {
        return pavimentoRepository.sumAreaExecutadaPorTorre(torreId);
    }

    public BigDecimal sumAreaExecutadaPorObra(String ceiObra) {
        return pavimentoRepository.sumAreaExecutadaPorObra(ceiObra);
    }

    private PavimentoResponseDTO convertToResponseDTO(PavimentoModel pavimento) {
        return new PavimentoResponseDTO(pavimento);
    }
}
