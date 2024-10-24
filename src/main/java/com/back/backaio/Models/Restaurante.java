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
@Table(name = "restaurante", schema = "aio")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurante_id")
    private Integer restauranteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "actividad_id", referencedColumnName = "actividad_id")
    private Actividad actividad;

    // Campos específicos de Restaurante
    private String tipoCocina;
    private String horario;
    private String direccion;
    private double valoracionMedia;
}
