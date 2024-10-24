package com.back.backaio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.List;

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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fechaRegistro")
    private Date fechaRegistro;


    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Viaje> viajes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    @ManyToMany
    @JoinTable(
            name = "usuario_grupo",
            schema = "aio",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos;

    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL)
    private List<Propuesta> propuestasCreadas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Voto> votosEmitidos;

}
