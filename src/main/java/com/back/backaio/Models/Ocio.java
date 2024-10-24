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
@Table(name = "ocio", schema = "aio")
public class Ocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocio_id")
    private Integer ocioId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id")
    private Actividad actividad;

    // Campos específicos de Ocio
    private String tipoActividad; // Ej: Teatro, Cine, Concierto
    private String horario;
    private String localizacion;
    private String requisitos; // Ej: Edad mínima, equipo especial
}
