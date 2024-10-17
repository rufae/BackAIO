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
@Table(name = "itinerario_sitios", schema = "AIO", catalog = "postgres")
public class ItinerariosSitios {
    @Id
    @ManyToOne
    @JoinColumn(name = "itinerario_id", nullable = false)
    private Itinerario itinerario;

    @Id
    @ManyToOne
    @JoinColumn(name = "sitio_id", nullable = false)
    private Sitios sitio;
}
