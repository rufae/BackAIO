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
@Table(name = "sitios", schema = "AIO")
public class Sitios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sitio_id")
    private Integer sitioId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo; // Podrías definir un enum si los tipos son limitados

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "pais")
    private String pais;

    @Column(name = "region")
    private String region;

    @Column(name = "distancia")
    private Float distancia;

    @Column(name = "calidad")
    private Integer calidad; // Asegúrate de manejar el rango (1-5)

    @Column(name = "reseñas_publicas")
    private String reseñasPublicas;
}
