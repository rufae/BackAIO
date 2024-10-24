package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

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
    private Integer actividadId;

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

    @OneToOne(mappedBy = "actividad", cascade = CascadeType.ALL)
    private Alojamiento alojamiento;

    @OneToOne(mappedBy = "actividad", cascade = CascadeType.ALL)
    private Excursion excursion;

    @OneToOne(mappedBy = "actividad", cascade = CascadeType.ALL)
    private Ocio ocio;

    @OneToOne(mappedBy = "actividad", cascade = CascadeType.ALL)
    private Restaurante restaurante;

    @ManyToMany(mappedBy = "actividades")
    private Set<Itinerario> itinerarios;
}
