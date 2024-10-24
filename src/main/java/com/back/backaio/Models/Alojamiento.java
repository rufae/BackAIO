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
@Table(name = "alojamiento", schema = "aio")
public class Alojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alojamiento_id")
    private Integer alojamientoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id")
    private Actividad actividad;

    // Campos específicos de Alojamiento
    private String tipo;
    private int capacidad;
}
