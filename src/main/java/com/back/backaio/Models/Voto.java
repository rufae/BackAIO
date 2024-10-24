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
    private Integer votoId;

    @Column(name = "votoAFavor")
    private boolean votoAFavor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "propuesta_id", referencedColumnName = "propuesta_id")
    private Propuesta propuesta;

}
