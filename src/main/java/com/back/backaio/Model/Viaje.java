package com.back.backaio.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "viaje", schema = "aio")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Integer viajeId;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaVuelta")
    private LocalDate fechaVuelta;

    @Column(name = "destino")
    private String destino;

    @Column(name = "numeroAdultos")
    private Integer numeroAdultos;

    @Column(name = "numeroMenores")
    private Integer numeroMenores;

    @Column(name = "numeroHabitaciones")
    private Integer numeroHabitaciones;

    @Column(name = "presupuesto")
    private Double presupuesto;


    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "viaje_itinerario",
            schema = "aio",
            joinColumns = @JoinColumn(name = "viaje_id"),
            inverseJoinColumns = @JoinColumn(name = "itinerario_id")
    )
    private Set<Itinerario> itinerarios;

}
