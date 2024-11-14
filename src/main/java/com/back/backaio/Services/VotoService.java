package com.back.backaio.Services;

import com.back.backaio.Repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    public boolean checkVoto(Long actividadId, Long usuarioId) {
        return votoRepository.existsByActividad_ActividadIdAndUsuario_UsuarioId(actividadId, usuarioId);
    }
}
