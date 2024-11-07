package com.back.backaio.Services;

import com.back.backaio.Models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    List<Usuario> listarAmigos(Long usuarioId);

    List<Grupo> listarGruposPorUsuario(Long usuarioId);

    Propuesta proponerActividad(Long usuarioId, ActividadDTO actividadDTO);

    VotoDTO votarActividad(Long actividadId, Long usuarioId, Boolean votoAFavor);

    List<ActividadConVotosDTO> verActividades(Long grupoId);
}
