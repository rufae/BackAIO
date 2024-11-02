package com.back.backaio.Services;

import com.back.backaio.Models.Grupo;
import com.back.backaio.Models.Viaje;
import org.springframework.stereotype.Service;

@Service
public interface IGrupoService {

    Grupo crearGrupo(Grupo grupo);

    Grupo anyadirParticipante(Long grupoId, Long usuarioId);

    Grupo verParticipantesViajes(Long grupoId);

    Grupo eliminarParticipantesViajes(Long grupoId, Long usuarioId);

}
