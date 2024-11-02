package com.back.backaio.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActividadDTO {
    private String calidad;
    private String distancia;
    private String pais;
    private String region;
    private String resenas;
    private boolean guardada;
    private TipoActividad tipoActividad;

}
