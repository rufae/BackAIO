package com.back.backaio.DTO;

import com.back.backaio.Model.TipoActividad;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ActividadDTO {

    private Long grupo_id;
    private String calidad;
    private String distancia;
    private String pais;
    private String region;
    private String resenas;
    private boolean guardada;
    private TipoActividad tipoActividad;

}
