package com.back.backaio.DTO;

import com.back.backaio.Model.TipoActividad;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
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