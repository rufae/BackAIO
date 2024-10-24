package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "excursion", schema = "aio")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private Integer excursionId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id")
    private Actividad actividad;

    // Campos específicos de Excursion
    private String lugarSalida;
    private String duracion;
    private String guia; // Nombre del guía asignado
    private String equipoRequerido; // Ej: Zapatos cómodos, linterna
}
