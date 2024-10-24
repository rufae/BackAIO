package com.back.backaio.Models;

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
@Table(name = "grupo", schema = "aio")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupo_id")
    private Integer grupoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechaCreacion")
    private Date fechaCreacion;

    @ManyToMany(mappedBy = "grupos")
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private Set<Propuesta> propuestas;

}
