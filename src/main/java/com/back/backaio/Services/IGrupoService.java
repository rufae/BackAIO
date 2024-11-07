package com.back.backaio.Services;

import com.back.backaio.Model.Grupo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGrupoService {

    List<Grupo> obtenerTodosLosGrupos();

    Grupo crearGrupo(Grupo grupo);

    Grupo anyadirParticipante(Long grupoId, Long usuarioId);

    Grupo verParticipantesViajes(Long grupoId);

    Grupo eliminarParticipantesViajes(Long grupoId, Long usuarioId);

}
