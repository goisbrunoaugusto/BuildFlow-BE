package com.buildflow.usuario;

import com.buildflow.usuario.dto.UsuarioCreateDTO;
import com.buildflow.usuario.dto.UsuarioResponseDTO;
import com.buildflow.usuario.dto.UsuarioUpdateDTO;
import com.buildflow.usuario.enums.UsuarioFuncao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> findAllActive() {
        return usuarioRepository.findByAtivoTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> findByEmail(String email) {
        return usuarioRepository.findByEmailAndAtivoTrue(email)
                .map(this::convertToResponseDTO);
    }

    public Optional<UsuarioResponseDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .filter(UsuarioModel::isAtivo)
                .map(this::convertToResponseDTO);
    }

    public List<UsuarioResponseDTO> findByFuncao(UsuarioFuncao funcao) {
        return usuarioRepository.findByFuncaoAndAtivoTrue(funcao)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> findByTermoBusca(String termo) {
        return usuarioRepository.findByTermoBusca(termo)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO create(UsuarioCreateDTO createDTO) {
        if (usuarioRepository.existsByEmailAndAtivoTrue(createDTO.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário ativo com o email: " + createDTO.getEmail());
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(createDTO.getNome());
        usuario.setSobrenome(createDTO.getSobrenome());
        usuario.setEmail(createDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(createDTO.getSenha()));
        usuario.setFuncao(createDTO.getFuncao());

        UsuarioModel savedUsuario = usuarioRepository.save(usuario);
        return convertToResponseDTO(savedUsuario);
    }

    public Optional<UsuarioResponseDTO> update(Long id, UsuarioUpdateDTO updateDTO) {
        return usuarioRepository.findById(id)
                .filter(UsuarioModel::isAtivo)
                .map(usuario -> {
                    if (updateDTO.getNome() != null) {
                        usuario.setNome(updateDTO.getNome());
                    }
                    if (updateDTO.getSobrenome() != null) {
                        usuario.setSobrenome(updateDTO.getSobrenome());
                    }
                    if (updateDTO.getEmail() != null && !updateDTO.getEmail().equals(usuario.getEmail())) {
                        if (usuarioRepository.existsByEmailAndAtivoTrue(updateDTO.getEmail())) {
                            throw new IllegalArgumentException("Já existe um usuário ativo com o email: " + updateDTO.getEmail());
                        }
                        usuario.setEmail(updateDTO.getEmail());
                    }
                    if (updateDTO.getSenha() != null) {
                        usuario.setSenha(passwordEncoder.encode(updateDTO.getSenha()));
                    }
                    if (updateDTO.getFuncao() != null) {
                        usuario.setFuncao(updateDTO.getFuncao());
                    }
                    if (updateDTO.getAtivo() != null) {
                        usuario.setAtivo(updateDTO.getAtivo());
                    }

                    UsuarioModel savedUsuario = usuarioRepository.save(usuario);
                    return convertToResponseDTO(savedUsuario);
                });
    }

    public boolean delete(Long id) {
        return usuarioRepository.findById(id)
                .filter(UsuarioModel::isAtivo)
                .map(usuario -> {
                    usuario.setAtivo(false);
                    usuarioRepository.save(usuario);
                    return true;
                })
                .orElse(false);
    }

    public long countActiveUsuarios() {
        return usuarioRepository.countUsuariosAtivos();
    }

    public long countUsuariosPorFuncao(UsuarioFuncao funcao) {
        return usuarioRepository.countUsuariosPorFuncao(funcao);
    }

    private UsuarioResponseDTO convertToResponseDTO(UsuarioModel usuario) {
        return new UsuarioResponseDTO(usuario);
    }
}
