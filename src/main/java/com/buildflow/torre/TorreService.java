package com.buildflow.torre;

import com.buildflow.obra.ObraModel;
import com.buildflow.obra.ObraRepository;
import com.buildflow.torre.dto.TorreCreateDTO;
import com.buildflow.torre.dto.TorreResponseDTO;
import com.buildflow.torre.dto.TorreUpdateDTO;
import com.buildflow.torre.enums.TorreStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TorreService {

    @Autowired
    private TorreRepository torreRepository;

    @Autowired
    private ObraRepository obraRepository;

    public List<TorreResponseDTO> findAllActive() {
        return torreRepository.findByAtivoTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<TorreResponseDTO> findById(Long id) {
        return torreRepository.findByIdAndAtivoTrue(id)
                .map(this::convertToResponseDTO);
    }

    public List<TorreResponseDTO> findByNome(String nome) {
        return torreRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TorreResponseDTO> findByStatus(TorreStatus status) {
        return torreRepository.findByStatusTorreAndAtivoTrue(status)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TorreResponseDTO> findByObra(String ceiObra) {
        return torreRepository.findByObraCeiAndAtivoTrue(ceiObra)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TorreResponseDTO> findTorresEmAndamento() {
        return torreRepository.findTorresEmAndamento(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TorreResponseDTO> findTorresConcluidas() {
        return torreRepository.findTorresConcluidas(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public TorreResponseDTO create(TorreCreateDTO createDTO) {
        ObraModel obra = obraRepository.findByCeiAndAtivoTrue(createDTO.getCeiObra())
                .orElseThrow(() -> new IllegalArgumentException("Obra não encontrada com o CEI: " + createDTO.getCeiObra()));

        TorreModel torre = new TorreModel();
        torre.setNome(createDTO.getNome());
        torre.setStatusTorre(createDTO.getStatusTorre());
        torre.setPavimentos(createDTO.getPavimentos());
        torre.setDataInicio(createDTO.getDataInicio());
        torre.setDataFim(createDTO.getDataFim());
        torre.setObra(obra);

        TorreModel savedTorre = torreRepository.save(torre);
        return convertToResponseDTO(savedTorre);
    }

    public Optional<TorreResponseDTO> update(Long id, TorreUpdateDTO updateDTO) {
        return torreRepository.findByIdAndAtivoTrue(id)
                .map(torre -> {
                    if (updateDTO.getNome() != null) {
                        torre.setNome(updateDTO.getNome());
                    }
                    if (updateDTO.getStatusTorre() != null) {
                        torre.setStatusTorre(updateDTO.getStatusTorre());
                    }
                    if (updateDTO.getPavimentos() != null) {
                        torre.setPavimentos(updateDTO.getPavimentos());
                    }
                    if (updateDTO.getDataInicio() != null) {
                        torre.setDataInicio(updateDTO.getDataInicio());
                    }
                    if (updateDTO.getDataFim() != null) {
                        torre.setDataFim(updateDTO.getDataFim());
                    }
                    if (updateDTO.getAtivo() != null) {
                        torre.setAtivo(updateDTO.getAtivo());
                    }

                    TorreModel savedTorre = torreRepository.save(torre);
                    return convertToResponseDTO(savedTorre);
                });
    }

    public boolean delete(Long id) {
        return torreRepository.findByIdAndAtivoTrue(id)
                .map(torre -> {
                    torre.setAtivo(false);
                    torreRepository.save(torre);
                    return true;
                })
                .orElse(false);
    }

    public long countActiveTorres() {
        return torreRepository.countTorresAtivas();
    }

    public long countTorresPorStatus(TorreStatus status) {
        return torreRepository.countTorresPorStatus(status);
    }

    public long countTorresPorObra(String ceiObra) {
        return torreRepository.countTorresPorObra(ceiObra);
    }

    private TorreResponseDTO convertToResponseDTO(TorreModel torre) {
        return new TorreResponseDTO(torre);
    }
}
