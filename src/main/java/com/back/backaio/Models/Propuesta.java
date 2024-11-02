package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "propuesta", schema = "aio")
public class Propuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propuesta_id")
    private Integer propuestaId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechaPropuesta")
    private Date fechaPropuesta;

    @Column(name = "aprobada")
    private boolean aprobada;


    @ManyToOne
    @JoinColumn(name = "creador_id", referencedColumnName = "usuario_id")
    private Usuario creador;

    @ManyToOne
    @JoinColumn(name = "grupo_id", referencedColumnName = "grupo_id")
    private Grupo grupo;

    @OneToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id")
    private Actividad actividad;

    @OneToMany(mappedBy = "propuesta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voto> votos;


}
