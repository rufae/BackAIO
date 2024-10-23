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
@Table(name = "usuario", schema = "aio")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", nullable = false)
    private Clientes cliente;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "rol")
    private String rol;

    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;
}
