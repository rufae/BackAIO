package com.back.backaio.Services;

import com.back.backaio.Models.*;
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
    public List<Usuario> listarAmigos(Long usuarioId) {
        return usuarioRepository.obtenerAmigos(usuarioId);
    }

    @Override
    public List<Grupo> listarGruposPorUsuario(Long usuarioId) {
        if (usuarioRepository.existsById(usuarioId)) {
            return grupoRepository.findGruposByUsuarioId(usuarioId);
        }
        return Collections.emptyList();
    }

    public Propuesta proponerActividad(Long usuarioId, ActividadDTO actividadDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Actividad actividad = new Actividad();
        actividad.setCalidad(actividadDTO.getCalidad());
        actividad.setDistancia(actividadDTO.getDistancia());
        actividad.setPais(actividadDTO.getPais());
        actividad.setRegion(actividadDTO.getRegion());
        actividad.setResenas(actividadDTO.getResenas());
        actividad.setGuardada(actividadDTO.isGuardada());
        actividad.setTipoActividad(actividadDTO.getTipoActividad());

        actividad = actividadRepository.save(actividad);

        Propuesta propuesta = new Propuesta();
        propuesta.setDescripcion("Propuesta de actividad por el usuario " + usuario.getUsername());
        propuesta.setFechaPropuesta(new Date());
        propuesta.setAprobada(false);
        propuesta.setCreador(usuario);
        propuesta.setActividad(actividad);

        return propuestaRepository.save(propuesta);
    }

    public Voto votarActividad(Long actividadId, Long usuarioId, Boolean votoAFavor) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Voto voto = new Voto();
        voto.setActividad(actividad);
        voto.setUsuario(usuario);
        voto.setVotoAFavor(votoAFavor);

        return votoRepository.save(voto);
    }

    public List<ActividadConVotosDTO> verActividades(Long grupoId) {

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        List<Propuesta> propuestas = propuestaRepository.findByGrupo(grupo);

        return propuestas.stream().map(propuesta -> {
            Actividad actividad = propuesta.getActividad();

            Long votosAFavor =  propuesta.getVotos().stream().filter(Voto::isVotoAFavor).count();
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

            return dto;
        }).collect(Collectors.toList());
    }
}
