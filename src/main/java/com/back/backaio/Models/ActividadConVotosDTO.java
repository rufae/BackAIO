package com.back.backaio.Models;

import lombok.Data;

@Data
public class ActividadConVotosDTO {
    private Integer actividadId;
    private String calidad;
    private String distancia;
    private String pais;
    private String region;
    private String resenas;
    private TipoActividad tipoActividad;
    private Long votosAFavor;
    private Long votosEnContra;
}
