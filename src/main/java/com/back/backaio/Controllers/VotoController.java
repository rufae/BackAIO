package com.back.backaio.Controllers;

import com.back.backaio.Services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @GetMapping("/usuario-ya-voto")
    public ResponseEntity<Boolean> usuarioYaVoto(@RequestParam Long actividadId, @RequestParam Long usuarioId) {
        boolean yaVoto = votoService.checkVoto(actividadId, usuarioId);
        return ResponseEntity.ok(yaVoto);
    }
}

