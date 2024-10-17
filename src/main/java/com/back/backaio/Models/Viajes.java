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
@Table(name = "viajes", schema = "AIO", catalog = "postgres")
public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Integer viajeId;

    @Column(name = "usuario_id")
    private Integer usuarioId; // Referencia a la tabla Usuario

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_vuelta")
    @Temporal(TemporalType.DATE)
    private Date fechaVuelta;

    @Column(name = "numero_adultos")
    private Integer numeroAdultos;

    @Column(name = "numero_ninos")
    private Integer numeroNinos;

    @Column(name = "numero_habitaciones")
    private Integer numeroHabitaciones;
}
