package com.back.backaio.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "actividad", schema = "aio")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actividad_id")
    private Long actividadId;

    @Column(name = "calidad")
    private String calidad;

    @Column(name = "distancia")
    private String distancia;

    @Column(name = "pais")
    private String pais;

    @Column(name = "region")
    private String region;

    @Column(name = "resenas")
    private String resenas;

    @Column(name = "guardada")
    private boolean guardada;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_actividad", nullable = false)
    private TipoActividad tipoActividad;

}
