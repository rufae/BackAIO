package com.back.backaio.Services;

import com.back.backaio.DTO.ActividadConVotosDTO;
import com.back.backaio.DTO.ActividadDTO;
import com.back.backaio.DTO.UsuarioDTO;
import com.back.backaio.DTO.VotoDTO;
import com.back.backaio.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    List<UsuarioDTO> listarAmigos(Long usuarioId);

    List<Grupo> listarGruposPorUsuario(Long usuarioId);

    Propuesta proponerActividad(Long usuarioId, ActividadDTO actividadDTO);

    VotoDTO votarActividad(Long actividadId, Long usuarioId, Boolean votoAFavor);

    List<ActividadConVotosDTO> verActividades(Long grupoId);
}
