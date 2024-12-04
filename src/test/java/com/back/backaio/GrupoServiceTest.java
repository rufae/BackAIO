package com.back.backaio;

import com.back.backaio.Model.Grupo;
import com.back.backaio.Model.Usuario;
import com.back.backaio.Repository.GrupoRepository;
import com.back.backaio.Repository.UsuarioRepository;
import com.back.backaio.Services.GrupoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class GrupoServiceTest {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCrearGrupo() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setUsername("Usuario Test");
        usuarioRepository.save(usuario);

        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");

        // WHEN
        Grupo resultado = grupoService.crearGrupo(grupo, usuario.getUsuarioId());

        // THEN
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Grupo Test", resultado.getNombre());
        Assertions.assertNotNull(resultado.getUsuarios());
        Assertions.assertEquals(1, resultado.getUsuarios().size());
        Assertions.assertTrue(resultado.getUsuarios().stream()
                .anyMatch(u -> u.getUsername().equals("Usuario Test")));
    }

    @Test
    public void testCrearGrupoUsuarioNoExiste() {
        // GIVEN
        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");

        // WHEN / THEN
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> grupoService.crearGrupo(grupo, 999L)
        );

        Assertions.assertEquals("No existe el usuario", exception.getMessage());
    }

    @Test
    public void testCrearGrupoValidaciones() {
        // GIVEN
        Grupo grupo = new Grupo(); // Grupo sin nombre ni descripción.

        // WHEN / THEN
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> grupoService.crearGrupo(grupo, 1L)
        );

        Assertions.assertEquals("El grupo debe tener un nombre válido", exception.getMessage());
    }

    @Test
    public void testAnyadirParticipante() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setUsername("Usuario Test");
        usuarioRepository.save(usuario);

        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");
        grupoRepository.save(grupo); // Persistimos el grupo primero

        grupo.getUsuarios().add(usuario); // Modificamos después de persistir
        grupoRepository.save(grupo);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername("Usuario Nuevo");
        usuarioRepository.save(nuevoUsuario);

        // WHEN
        Grupo resultado = grupoService.anyadirParticipante(grupo.getGrupoId(), nuevoUsuario.getUsuarioId());

        // THEN
        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.getUsuarios().stream()
                .anyMatch(u -> u.getUsername().equals("Usuario Nuevo")));
    }


    @Test
    public void testAnyadirParticipanteYaExistente() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setUsername("Usuario Test");
        usuarioRepository.save(usuario);

        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");
        grupo.getUsuarios().add(usuario);
        grupoRepository.save(grupo);

        // WHEN / THEN
        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> grupoService.anyadirParticipante(grupo.getGrupoId(), usuario.getUsuarioId())
        );

        Assertions.assertEquals("El usuario ya está en el grupo", exception.getMessage());
    }

    @Test
    public void testEliminarParticipantesViajes() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setUsername("Usuario Test");
        usuarioRepository.save(usuario);

        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");
        grupo.getUsuarios().add(usuario);
        grupoRepository.save(grupo);

        // WHEN
        Grupo resultado = grupoService.eliminarParticipantesViajes(grupo.getGrupoId(), usuario.getUsuarioId());

        // THEN
        Assertions.assertFalse(resultado.getUsuarios().contains(usuario));
    }

    @Test
    public void testEliminarParticipanteNoExistenteEnGrupo() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setUsername("Usuario Test");
        usuarioRepository.save(usuario);

        Grupo grupo = new Grupo();
        grupo.setNombre("Grupo Test");
        grupoRepository.save(grupo);

        // WHEN / THEN
        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> grupoService.eliminarParticipantesViajes(grupo.getGrupoId(), usuario.getUsuarioId())
        );

        Assertions.assertEquals("El usuario no pertenece al grupo", exception.getMessage());
    }

}
