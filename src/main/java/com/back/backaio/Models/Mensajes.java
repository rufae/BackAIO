package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "mensajes", schema = "aio")
public class Mensajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Integer mensajeId;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private Timestamp fechaEnvio;

    @Column(name = "imagen")
    private String imagen;
}
