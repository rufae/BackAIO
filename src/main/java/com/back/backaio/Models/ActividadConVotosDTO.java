package com.back.backaio.Models;

import lombok.Data;

import java.util.List;

@Data
public class ActividadConVotosDTO {
    private Long actividadId;
    private String calidad;
    private String distancia;
    private String pais;
    private String region;
    private String resenas;
    private TipoActividad tipoActividad;
    private Long votosAFavor;
    private Long votosEnContra;
    private List<VotoDTO> votos;
}
