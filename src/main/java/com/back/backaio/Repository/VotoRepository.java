package com.back.backaio.Repository;

import com.back.backaio.Model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByActividad_ActividadIdAndUsuario_UsuarioId(Long actividadId, Long usuarioId);
}

