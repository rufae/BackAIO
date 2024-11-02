package com.back.backaio.Services;

import com.back.backaio.Models.Grupo;
import com.back.backaio.Models.Usuario;
import com.back.backaio.Repository.GrupoRepository;
import com.back.backaio.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class GrupoService implements IGrupoService{

    private final GrupoRepository gruporepository;
    private final UsuarioRepository usuariorepository;

    public GrupoService(GrupoRepository gruporepository, UsuarioRepository usuariorepository) {
        this.gruporepository = gruporepository;
        this.usuariorepository = usuariorepository;
    }

    @Override
    public Grupo crearGrupo(Grupo grupo) {
        return gruporepository.save(grupo);
    }

    @Override
    public Grupo anyadirParticipante(Long grupoId, Long usuarioId) {
        Grupo grupo = gruporepository.findById(grupoId).orElseThrow(() -> new RuntimeException("No existe el grupo"));
        Usuario participante = usuariorepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("No existe el usuario"));

        grupo.getUsuarios().add(participante);
        return gruporepository.save(grupo);

    }

    @Override
    public Grupo verParticipantesViajes(Long grupoId) {
        return gruporepository.findById(grupoId).orElse(null);
    }

    @Override
    public Grupo eliminarParticipantesViajes(Long grupoId, Long usuarioId) {
        Grupo grupo = gruporepository.findById(grupoId).orElseThrow(() -> new RuntimeException("No existe el grupo"));
        Usuario participante = usuariorepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("No existe el usuario"));
        grupo.getUsuarios().remove(participante);

        return gruporepository.save(grupo);
    }
}
