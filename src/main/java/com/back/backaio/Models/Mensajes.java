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
@Table(name = "mensajes", schema = "AIO", catalog = "postgres")
public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mensaje_id")
    private Integer mensajeId;

    @Column(name = "chat_id")
    private Integer chatId; // Referencia a la tabla Chat

    @Column(name = "usuario_id")
    private Integer usuarioId; // Referencia a la tabla Usuario

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Column(name = "imagen")
    private byte[] imagen;
}
