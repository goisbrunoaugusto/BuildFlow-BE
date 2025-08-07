package com.buildflow.obra;

import com.buildflow.construtora.ConstrutoraService;
import com.buildflow.obra.dto.ObraCreateDTO;
import com.buildflow.obra.dto.ObraResponseDTO;
import com.buildflow.obra.dto.ObraUpdateDTO;
import com.buildflow.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private ConstrutoraService construtoraService;

    public List<ObraResponseDTO> findAllActive() {
        return obraRepository.findByAtivoTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ObraResponseDTO> findByCei(String cei) {
        return obraRepository.findByCeiAndAtivoTrue(cei)
                .map(this::convertToResponseDTO);
    }

    public List<ObraResponseDTO> findByConstrutora(String construtora) {
        return obraRepository.findByConstrutoraNomeContainingIgnoreCaseAndAtivoTrue(construtora)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ObraResponseDTO> findObrasEmAndamento() {
        return obraRepository.findObrasEmAndamento(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ObraResponseDTO> findObrasConcluidas() {
        return obraRepository.findObrasConcluidas(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ObraResponseDTO create(ObraCreateDTO createDTO, UsuarioModel usuarioCriador) {
        if (obraRepository.existsByCeiAndAtivoTrue(createDTO.getCei())) {
            throw new IllegalArgumentException("Já existe uma obra ativa com o CEI: " + createDTO.getCei());
        }

        ObraModel obra = new ObraModel();
        obra.setCei(createDTO.getCei());
        obra.setNome(createDTO.getNome());
        obra.addConstrutora(construtoraService.findOrCreateByNome(createDTO.getConstrutora()));
        obra.setValorM2(createDTO.getValorM2());
        obra.setTotalGeral(createDTO.getTotalGeral());
        obra.setDataInicio(createDTO.getDataInicio());
        obra.setDataFim(createDTO.getDataFim());
        obra.setLocal(createDTO.getLocal());
        obra.setUsuarioCriador(usuarioCriador);

        ObraModel savedObra = obraRepository.save(obra);
        return convertToResponseDTO(savedObra);
    }

    public Optional<ObraResponseDTO> update(String cei, ObraUpdateDTO updateDTO) {
        return obraRepository.findByCeiAndAtivoTrue(cei)
                .map(obra -> {
                    if (updateDTO.getNome() != null) {
                        obra.setNome(updateDTO.getNome());
                    }
                    if (updateDTO.getConstrutora() != null) {
                        if (obra.getConstrutoras() != null) {
                            obra.getConstrutoras().clear();
                        }
                        obra.addConstrutora(construtoraService.findOrCreateByNome(updateDTO.getConstrutora()));
                    }
                    if (updateDTO.getValorM2() != null) {
                        obra.setValorM2(updateDTO.getValorM2());
                    }
                    if (updateDTO.getTotalGeral() != null) {
                        obra.setTotalGeral(updateDTO.getTotalGeral());
                    }
                    if (updateDTO.getDataInicio() != null) {
                        obra.setDataInicio(updateDTO.getDataInicio());
                    }
                    if (updateDTO.getDataFim() != null) {
                        obra.setDataFim(updateDTO.getDataFim());
                    }
                    if (updateDTO.getLocal() != null) {
                        obra.setLocal(updateDTO.getLocal());
                    }

                    ObraModel savedObra = obraRepository.save(obra);
                    return convertToResponseDTO(savedObra);
                });
    }

    public boolean delete(String cei) {
        return obraRepository.findByCeiAndAtivoTrue(cei)
                .map(obra -> {
                    obra.setAtivo(false);
                    obraRepository.save(obra);
                    return true;
                })
                .orElse(false);
    }

    public long countActiveObras() {
        return obraRepository.countObrasAtivas();
    }

    private ObraResponseDTO convertToResponseDTO(ObraModel obra) {
        // Pegar a primeira construtora (construtora principal) para compatibilidade
        String construtoraNome = null;
        Long construtoraId = null;
        if (obra.getConstrutoras() != null && !obra.getConstrutoras().isEmpty()) {
            construtoraNome = obra.getConstrutoras().get(0).getNome();
            construtoraId = obra.getConstrutoras().get(0).getId();
        }
        
        return new ObraResponseDTO(
                obra.getCei(),
                obra.getNome(),
                construtoraNome,
                construtoraId,
                obra.getValorM2(),
                obra.getTotalGeral(),
                obra.getDataInicio(),
                obra.getDataFim(),
                obra.getLocal(),
                obra.getDataCriacao(),
                obra.isAtivo(),
                obra.isEmAndamento(),
                obra.isConcluida(),
                obra.getUsuarioCriador() != null ? obra.getUsuarioCriador().getNomeCompleto() : null
        );
    }
}
