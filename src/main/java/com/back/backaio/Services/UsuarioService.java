package com.back.backaio.Services;

import com.back.backaio.DTO.ActividadConVotosDTO;
import com.back.backaio.DTO.ActividadDTO;
import com.back.backaio.DTO.UsuarioDTO;
import com.back.backaio.DTO.VotoDTO;
import com.back.backaio.Model.*;
import com.back.backaio.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final GrupoRepository grupoRepository;
    @Autowired
    private final ActividadRepository actividadRepository;
    @Autowired
    private final PropuestaRepository propuestaRepository;
    @Autowired
    private final VotoRepository votoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, GrupoRepository grupoRepository, ActividadRepository actividadRepository, PropuestaRepository propuestaRepository, VotoRepository votoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.actividadRepository = actividadRepository;
        this.propuestaRepository = propuestaRepository;
        this.votoRepository = votoRepository;
    }

    @Override
    public List<UsuarioDTO> listarAmigos(Long usuarioId) {
        List<Usuario> amigos = usuarioRepository.obtenerAmigos(usuarioId);
        return amigos.stream()
                .filter(amigo -> !amigo.getUsuarioId().equals(usuarioId))
                .map(amigo -> new UsuarioDTO(amigo.getUsuarioId(), amigo.getUsername(), amigo.getFechaRegistro(), amigo.getBio(), amigo.getImagen()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Grupo> listarGruposPorUsuario(Long usuarioId) {
        if (usuarioRepository.existsById(usuarioId)) {
            return grupoRepository.findGruposByUsuarioId(usuarioId);
        }
        return Collections.emptyList();
    }

    public Propuesta proponerActividad(Long usuarioId, ActividadDTO actividadDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Actividad actividad = new Actividad();
        actividad.setCalidad(actividadDTO.getCalidad());
        actividad.setDistancia(actividadDTO.getDistancia());
        actividad.setPais(actividadDTO.getPais());
        actividad.setRegion(actividadDTO.getRegion());
        actividad.setResenas(actividadDTO.getResenas());
        actividad.setGuardada(actividadDTO.isGuardada());
        actividad.setTipoActividad(actividadDTO.getTipoActividad());

        actividad = actividadRepository.save(actividad);


        Grupo grupo = grupoRepository.findById(actividadDTO.getGrupo_id())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Propuesta propuesta = new Propuesta();
        propuesta.setDescripcion("Propuesta de actividad por el usuario " + usuario.getUsername());
        propuesta.setFechaPropuesta(new Date());
        propuesta.setAprobada(false);
        propuesta.setCreador(usuario);
        propuesta.setActividad(actividad);
        propuesta.setGrupo(grupo);

        return propuestaRepository.save(propuesta);
    }

    public VotoDTO votarActividad(Long actividadId, Long usuarioId, Boolean votoAFavor) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Propuesta propuesta = propuestaRepository.findByActividad(actividad)
                .orElseThrow(() -> new RuntimeException("Propuesta no encontrada para esta actividad"));

        Voto voto = new Voto();
        voto.setActividad(actividad);
        voto.setUsuario(usuario);
        voto.setVotoAFavor(votoAFavor);
        voto.setPropuesta(propuesta);

        voto = votoRepository.save(voto);

        // Transformar Voto en VotoDTO antes de devolverlo
        return new VotoDTO(voto.getVotoId(), voto.isVotoAFavor(), actividad.getActividadId(), usuario.getUsuarioId());
    }


    public List<ActividadConVotosDTO> verActividades(Long grupoId) {

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        List<Propuesta> propuestas = propuestaRepository.findByGrupo(grupo);

        return propuestas.stream().map(propuesta -> {
            Actividad actividad = propuesta.getActividad();

            Long votosAFavor = propuesta.getVotos().stream().filter(Voto::isVotoAFavor).count();
            Long votosEnContra = propuesta.getVotos().stream().filter(voto -> !voto.isVotoAFavor()).count();

            ActividadConVotosDTO dto = new ActividadConVotosDTO();
            dto.setActividadId(actividad.getActividadId());
            dto.setCalidad(actividad.getCalidad());
            dto.setDistancia(actividad.getDistancia());
            dto.setPais(actividad.getPais());
            dto.setRegion(actividad.getRegion());
            dto.setResenas(actividad.getResenas());
            dto.setTipoActividad(actividad.getTipoActividad());
            dto.setVotosAFavor(votosAFavor);
            dto.setVotosEnContra(votosEnContra);

            // Convertir los votos de la propuesta a una lista de VotoDTO y asignarla al DTO de actividad
            List<VotoDTO> votosDTO = propuesta.getVotos().stream()
                    .map(voto -> new VotoDTO(voto.getVotoId(), voto.isVotoAFavor(), voto.getActividad().getActividadId(), voto.getUsuario().getUsuarioId()))
                    .collect(Collectors.toList());

            dto.setVotos(votosDTO); // Asegúrate de que ActividadConVotosDTO tiene este campo

            return dto;
        }).collect(Collectors.toList());
    }

    public Usuario validarCredenciales(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UsuarioDTO(usuario.getUsuarioId(), usuario.getUsername(), usuario.getFechaRegistro(), usuario.getBio(), usuario.getImagen());
    }
}
