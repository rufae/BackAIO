package com.back.backaio.Model;

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
@Table(name = "mensaje", schema = "aio")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Integer mensajeId;

    @Column(name = "contenido")
    private String content;

    @Column(name = "fechaEnvio")
    private Date fechaEnvio;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

}
