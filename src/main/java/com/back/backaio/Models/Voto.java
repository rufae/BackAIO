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
@Table(name = "voto", schema = "aio")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voto_id")
    private Long votoId;

    @Column(name = "votoAFavor")
    private boolean votoAFavor;


    @ManyToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id", nullable = false)
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "propuesta_id", referencedColumnName = "propuesta_id", nullable = false)
    private Propuesta propuesta;

}
