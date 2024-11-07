package com.back.backaio.Repository;

import com.back.backaio.Model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    @Query("SELECT g FROM Grupo g JOIN g.usuarios u WHERE u.usuarioId = :usuarioId")
    List<Grupo> findGruposByUsuarioId(@Param("usuarioId") Long usuarioId);
}
