package com.back.backaio.Repository;

import com.back.backaio.Models.Actividad;
import com.back.backaio.Models.Grupo;
import com.back.backaio.Models.Propuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropuestaRepository extends JpaRepository<Propuesta, Integer> {
    List<Propuesta> findByGrupo(Grupo grupo);
    Optional<Propuesta> findByActividad(Actividad actividad);
}
