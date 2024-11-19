package com.back.backaio.Controllers;


import com.back.backaio.DTO.*;
import com.back.backaio.Model.*;
import com.back.backaio.Services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/amigos")
    public ResponseEntity<List<UsuarioDTO>> listarAmigos(@RequestParam Long usuarioId) {
        List<UsuarioDTO> amigos = usuarioService.listarAmigos(usuarioId);
        if (!amigos.isEmpty()) {
            return ResponseEntity.ok(amigos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/viaje")
    public ResponseEntity<List<Grupo>> listarViaje(@RequestParam Long usuarioId){

        List<Grupo> grupos = usuarioService.listarGruposPorUsuario(usuarioId);

        if (!grupos.isEmpty()) {
            return ResponseEntity.ok(grupos);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("viaje/actividad/nueva")
    public ResponseEntity<Propuesta> proponerActividad(@RequestParam Long usuarioId, @RequestBody ActividadDTO actividadDTO) {
        Propuesta propuesta = usuarioService.proponerActividad(usuarioId, actividadDTO);
        return ResponseEntity.ok(propuesta);
    }

    @PostMapping("/viaje/actividad/votar")
    public ResponseEntity<VotoDTO> votarActividad(@RequestParam Long actividadId, @RequestParam Long usuarioId, @RequestParam Boolean votoAFavor) {
        VotoDTO voto = usuarioService.votarActividad(actividadId, usuarioId, votoAFavor);
        return ResponseEntity.ok(voto);
    }

    @GetMapping("/viaje/actividad")
    public ResponseEntity<List<ActividadConVotosDTO>> verActividades(@RequestParam Long grupoId) {
        List<ActividadConVotosDTO> actividades = usuarioService.verActividades(grupoId);
        if (!actividades.isEmpty()) {
            return ResponseEntity.ok(actividades);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.validarCredenciales(loginRequest.getUsername(), loginRequest.getPassword());
        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        return ResponseEntity.ok(new LoginResponse(usuario.getUsuarioId(), "tokenEjemplo"));
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("usuarios/{id}/grupos")
    public ResponseEntity<List<Grupo>> obtenerGruposPorUsuario(@PathVariable Long id) {
        List<Grupo> grupos = usuarioService.listarGruposPorUsuario(id);
        if (!grupos.isEmpty()) {
            return ResponseEntity.ok(grupos);
        }
        return ResponseEntity.notFound().build();
    }
}
