package com.back.backaio.Services;

import com.back.backaio.Model.Grupo;
import com.back.backaio.Model.Usuario;
import com.back.backaio.Repository.GrupoRepository;
import com.back.backaio.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService implements IGrupoService{

    private final GrupoRepository gruporepository;
    private final UsuarioRepository usuariorepository;

    public GrupoService(GrupoRepository gruporepository, UsuarioRepository usuariorepository) {
        this.gruporepository = gruporepository;
        this.usuariorepository = usuariorepository;
    }

    @Override
    public List<Grupo> obtenerTodosLosGrupos() {
        return gruporepository.findAll();
    }

    public List<Grupo> obtenerGruposPorUsuario(Long usuarioId) {
        return gruporepository.findGruposByUsuarioId(usuarioId);
    }

    public Optional<Grupo> encontrarGrupoPorId(Long grupoId) {
        return gruporepository.findById(grupoId);
    }

    @Override
    public Grupo crearGrupo(Grupo grupo, Long usuarioId) {
        Usuario participante = usuariorepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("No existe el usuario"));
        if (grupo.getUsuarios() == null) {
            grupo.setUsuarios(new HashSet<>());
        }

        grupo.getUsuarios().add(participante);
        grupo.setFechaCreacion(LocalDate.now());
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
