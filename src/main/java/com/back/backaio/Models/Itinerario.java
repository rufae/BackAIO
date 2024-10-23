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
@Table(name = "itinerario", schema = "aio")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerario_id")
    private Integer itinerarioId;

    @Column(name = "fecha_ida", nullable = false)
    private Date fechaIda;

    @Column(name = "fecha_vuelta", nullable = false)
    private Date fechaVuelta;

    @ManyToOne
    @JoinColumn(name = "viaje_id", referencedColumnName = "viaje_id", nullable = false)
    private Viajes viaje;
}
