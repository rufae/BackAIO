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
@Table(name = "itinerario", schema = "AIO", catalog = "postgres")
public class Itinerario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerario_id")
    private Integer itinerarioId;

    @Column(name = "fecha_ida")
    @Temporal(TemporalType.DATE)
    private Date fechaIda;

    @Column(name = "fecha_vuelta")
    @Temporal(TemporalType.DATE)
    private Date fechaVuelta;

    @Column(name = "viaje_id")
    private Integer viajeId; // Referencia a la tabla Viajes
}
