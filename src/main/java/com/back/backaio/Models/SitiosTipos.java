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
@Table(name = "sitios_tipos", schema = "aio", catalog = "postgres")
public class SitiosTipos {

    @Id
    @ManyToOne
    @JoinColumn(name = "sitio_id", referencedColumnName = "sitio_id", nullable = false)
    private Sitios sitio;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "alojamiento_id", referencedColumnName = "alojamiento_id")
    private Alojamientos alojamiento;

    @ManyToOne
    @JoinColumn(name = "excursion_id", referencedColumnName = "excursion_id")
    private Excursiones excursion;

    @ManyToOne
    @JoinColumn(name = "ocio_id", referencedColumnName = "ocio_id")
    private Ocio ocio;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", referencedColumnName = "restaurante_id")
    private Restaurantes restaurante;
}
