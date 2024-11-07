package com.back.backaio.Models;

import lombok.Data;

@Data
public class VotoDTO {

    private Long votoId;
    private boolean votoAFavor;
    private Long actividadId;
    private Long usuarioId;

    public VotoDTO(Long votoId, boolean votoAFavor, Long actividadId, Long usuarioId) {
        this.votoId = votoId;
        this.votoAFavor = votoAFavor;
        this.actividadId = actividadId;
        this.usuarioId = usuarioId;
    }
}
