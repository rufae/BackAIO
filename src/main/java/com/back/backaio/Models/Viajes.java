package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "viajes", schema = "aio")
public class Viajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Integer viajeId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "lugar", nullable = false)
    private String lugar;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_vuelta", nullable = false)
    private Date fechaVuelta;

    @Column(name = "numero_adultos", nullable = false)
    private Integer numeroAdultos;

    @Column(name = "numero_ninos", nullable = false)
    private Integer numeroNinos;

    @Column(name = "numero_habitaciones")
    private Integer numeroHabitaciones;
}
