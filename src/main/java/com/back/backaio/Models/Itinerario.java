package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "itinerario", schema = "aio")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerario_id")
    private Integer itinerarioId;

    @Column(name = "fechaIda")
    private Date fechaIda;

    @Column(name = "fechaVuelta")
    private Date fechaVuelta;


    @ManyToMany
    @JoinTable(
            name = "itinerario_actividad",
            schema = "aio",
            joinColumns = @JoinColumn(name = "itinerario_id"),
            inverseJoinColumns = @JoinColumn(name = "actividad_id")
    )
    private Set<Actividad> actividades;

    @ManyToOne
    @JoinColumn(name = "usuario_creador_id", referencedColumnName = "usuario_id")
    private Usuario usuarioCreador;
}
