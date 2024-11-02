package com.back.backaio.Controllers;


import com.back.backaio.Models.Grupo;
import com.back.backaio.Services.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/viaje")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo nuevoGrupo) {
        Grupo grupocreado = grupoService.crearGrupo(nuevoGrupo);
        return ResponseEntity.ok(grupocreado);
    }

    @PostMapping("/participantes/nuevo")
    public ResponseEntity<Grupo> anyadirParticipantesViajes(@RequestParam Long grupoId, @RequestParam Long usuarioId) {
        Grupo grupoActualizado = grupoService.anyadirParticipante(grupoId, usuarioId);
        return ResponseEntity.ok(grupoActualizado);
    }

    @GetMapping("/participantes")
    public ResponseEntity<List<Grupo>> verParticipantesViaje(@RequestParam Long grupoId) {
        Grupo participantes = grupoService.verParticipantesViajes(grupoId);
        return ResponseEntity.ok(Collections.singletonList(participantes));
    }

    @DeleteMapping("/participantes/eliminar")
    public ResponseEntity<Grupo> eliminarParticipante(@RequestParam Long grupoId, @RequestParam Long usuarioId) {
        Grupo eliminarParticipante = grupoService.eliminarParticipantesViajes(grupoId, usuarioId);
        return ResponseEntity.ok(eliminarParticipante);
    }

}
