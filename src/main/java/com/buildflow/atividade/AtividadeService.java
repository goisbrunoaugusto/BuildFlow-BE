package com.buildflow.atividade;

import com.buildflow.atividade.dto.AtividadeCreateDTO;
import com.buildflow.atividade.dto.AtividadeResponseDTO;
import com.buildflow.atividade.dto.AtividadeUpdateDTO;
import com.buildflow.equipe.EquipeModel;
import com.buildflow.equipe.EquipeRepository;
import com.buildflow.pavimento.PavimentoModel;
import com.buildflow.pavimento.PavimentoRepository;
import com.buildflow.usuario.UsuarioModel;
import com.buildflow.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private PavimentoRepository pavimentoRepository;

    public List<AtividadeResponseDTO> findAllActive() {
        return atividadeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<AtividadeResponseDTO> findById(Long id) {
        return atividadeRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public List<AtividadeResponseDTO> findByUsuario(Long usuarioId) {
        return atividadeRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findByEquipe(Long equipeId) {
        return atividadeRepository.findByEquipeId(equipeId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findByPavimento(Long pavimentoId) {
        return atividadeRepository.findByPavimentoId(pavimentoId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findByTorre(Long torreId) {
        return atividadeRepository.findByPavimentoTorreId(torreId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findByObra(String ceiObra) {
        return atividadeRepository.findByPavimentoTorreObraCei(ceiObra)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findAtividadesHoje() {
        return atividadeRepository.findAtividadesHoje(LocalDate.now())
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findAtividadesPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return atividadeRepository.findByDataRealizadaBetween(dataInicio, dataFim)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findAtividadesComM2Minimo(BigDecimal m2Minimo) {
        return atividadeRepository.findAtividadesComM2Minimo(m2Minimo)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AtividadeResponseDTO> findAtividadesComM3Minimo(BigDecimal m3Minimo) {
        return atividadeRepository.findAtividadesComM3Minimo(m3Minimo)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public AtividadeResponseDTO create(AtividadeCreateDTO createDTO) {
        UsuarioModel usuario = usuarioRepository.findById(createDTO.getUsuarioId())
                .filter(UsuarioModel::isAtivo)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + createDTO.getUsuarioId()));

        EquipeModel equipe = equipeRepository.findById(createDTO.getEquipeId())
                .filter(EquipeModel::isAtivo)
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada com o ID: " + createDTO.getEquipeId()));

        PavimentoModel pavimento = pavimentoRepository.findById(createDTO.getPavimentoId())
                .filter(PavimentoModel::isAtivo)
                .orElseThrow(() -> new IllegalArgumentException("Pavimento não encontrado com o ID: " + createDTO.getPavimentoId()));

        AtividadeModel atividade = new AtividadeModel();
        atividade.setM2Executado(createDTO.getM2Executado());
        atividade.setM3ExecutadoArgamassa(createDTO.getM3ExecutadoArgamassa());
        atividade.setDataRealizada(createDTO.getDataRealizada() != null ? createDTO.getDataRealizada() : LocalDateTime.now());
        atividade.setUsuario(usuario);
        atividade.setEquipe(equipe);
        atividade.setPavimento(pavimento);

        // Atualizar o pavimento com os valores executados
        pavimento.adicionarM2Executada(createDTO.getM2Executado());
        pavimento.adicionarM3TotalArgamassa(createDTO.getM3ExecutadoArgamassa());
        pavimentoRepository.save(pavimento);

        AtividadeModel savedAtividade = atividadeRepository.save(atividade);
        return convertToResponseDTO(savedAtividade);
    }

    public Optional<AtividadeResponseDTO> update(Long id, AtividadeUpdateDTO updateDTO) {
        return atividadeRepository.findById(id)
                .map(atividade -> {
                    if (updateDTO.getM2Executado() != null) {
                        atividade.setM2Executado(updateDTO.getM2Executado());
                    }
                    if (updateDTO.getM3ExecutadoArgamassa() != null) {
                        atividade.setM3ExecutadoArgamassa(updateDTO.getM3ExecutadoArgamassa());
                    }
                    if (updateDTO.getDataRealizada() != null) {
                        atividade.setDataRealizada(updateDTO.getDataRealizada());
                    }

                    AtividadeModel savedAtividade = atividadeRepository.save(atividade);
                    return convertToResponseDTO(savedAtividade);
                });
    }

    public boolean delete(Long id) {
        return atividadeRepository.findById(id)
                .map(atividade -> {
                    atividadeRepository.delete(atividade);
                    return true;
                })
                .orElse(false);
    }

    public long countActiveAtividades() {
        return atividadeRepository.countAtividadesAtivas();
    }

    public long countAtividadesPorUsuario(Long usuarioId) {
        return atividadeRepository.countAtividadesPorUsuario(usuarioId);
    }

    public long countAtividadesPorEquipe(Long equipeId) {
        return atividadeRepository.countAtividadesPorEquipe(equipeId);
    }

    public long countAtividadesPorPavimento(Long pavimentoId) {
        return atividadeRepository.countAtividadesPorPavimento(pavimentoId);
    }

    public BigDecimal sumM2ExecutadoPorUsuario(Long usuarioId) {
        return atividadeRepository.sumM2ExecutadoPorUsuario(usuarioId);
    }

    public BigDecimal sumM2ExecutadoPorEquipe(Long equipeId) {
        return atividadeRepository.sumM2ExecutadoPorEquipe(equipeId);
    }

    public BigDecimal sumM2ExecutadoPorPavimento(Long pavimentoId) {
        return atividadeRepository.sumM2ExecutadoPorPavimento(pavimentoId);
    }

    public BigDecimal sumM3ExecutadoPorUsuario(Long usuarioId) {
        return atividadeRepository.sumM3ExecutadoPorUsuario(usuarioId);
    }

    public BigDecimal sumM3ExecutadoPorEquipe(Long equipeId) {
        return atividadeRepository.sumM3ExecutadoPorEquipe(equipeId);
    }

    public BigDecimal sumM3ExecutadoPorPavimento(Long pavimentoId) {
        return atividadeRepository.sumM3ExecutadoPorPavimento(pavimentoId);
    }

    private AtividadeResponseDTO convertToResponseDTO(AtividadeModel atividade) {
        return new AtividadeResponseDTO(atividade);
    }
}
