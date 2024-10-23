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
@Table(name = "sitios", schema = "aio")
public class Sitios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sitio_id")
    private Integer sitioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "tipo_especifico")
    private String tipoEspecifico;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "pais")
    private String pais;

    @Column(name = "region")
    private String region;

    @Column(name = "distancia")
    private Float distancia;

    @Column(name = "calidad")
    private Integer calidad;

    @Column(name = "reseñas_publicas")
    private String resenasPublicas;
}
