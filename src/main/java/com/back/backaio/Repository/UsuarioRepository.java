package com.back.backaio.Repository;

import com.back.backaio.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Usuario u JOIN u.amigos a " +
            "WHERE (u.usuarioId = :idOrigen AND a.usuarioId = :idDestino) " +
            "   OR (u.usuarioId = :idDestino AND a.usuarioId = :idOrigen)")
    boolean sonAmigos(@Param("idOrigen") Long idOrigen, @Param("idDestino") Long idDestino);

    @Query("SELECT a FROM Usuario u JOIN u.amigos a " +
            "WHERE u.usuarioId = :idUsuario OR a.usuarioId = :idUsuario")
    List<Usuario> obtenerAmigos(@Param("idUsuario") Long idUsuario);

}
