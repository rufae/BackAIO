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
@Table(name = "chat", schema = "AIO", catalog = "postgres")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;

    @Column(name = "grupo_id")
    private Integer grupoId; // Referencia a la tabla Grupos

    @Column(name = "usuario_id")
    private Integer usuarioId; // Referencia a la tabla Usuario
}
