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
@Table(name = "usuarios", schema = "AIO", catalog = "postgres")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "cliente_id")
    private Integer clienteId; // Este debe estar relacionado con la tabla Clientes

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "rol")
    private String rol;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
}
