package com.back.backaio.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

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
    private Long usuarioId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

    @ManyToMany
    @JoinTable(
            name = "amistad",
            joinColumns = @JoinColumn(name = "usuario_id_origen"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id_destino")
    )
    private Set<Usuario> amigos;

    public Usuario(Long usuarioId) {
    }
}
