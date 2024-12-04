package com.back.backaio;

import com.back.backaio.DTO.ActividadConVotosDTO;
import com.back.backaio.DTO.ActividadDTO;
import com.back.backaio.DTO.UsuarioDTO;
import com.back.backaio.DTO.VotoDTO;
import com.back.backaio.Model.*;
import com.back.backaio.Repository.*;
import com.back.backaio.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private GrupoRepository grupoRepository;
    @Mock
    private ActividadRepository actividadRepository;
    @Mock
    private PropuestaRepository propuestaRepository;
    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private Grupo grupo;
    private Actividad actividad;
    private Propuesta propuesta;
    private Voto voto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setUsuarioId(1L);
        usuario.setUsername("UsuarioTest");
        usuario.setPassword("password");

        grupo = new Grupo();
        grupo.setGrupoId(1L);
        grupo.setNombre("GrupoTest");

        actividad = new Actividad();
        actividad.setActividadId(1L);
        actividad.setNombre("ActividadTest");

        propuesta = new Propuesta();
        propuesta.setPropuestaId(1);
        propuesta.setActividad(actividad);
        propuesta.setGrupo(grupo);
        propuesta.setCreador(usuario);

        voto = new Voto();
        voto.setVotoId(1L);
        voto.setVotoAFavor(true);
        voto.setActividad(actividad);
        voto.setUsuario(usuario);
        voto.setPropuesta(propuesta);
    }

    @Test
    public void testListarAmigos() {
        List<Usuario> amigos = Arrays.asList(usuario);
        when(usuarioRepository.obtenerAmigos(1L)).thenReturn(amigos);

        List<UsuarioDTO> amigosDTO = usuarioService.listarAmigos(1L);

        assertNotNull(amigosDTO);
        assertEquals(1, amigosDTO.size());
        assertEquals("UsuarioTest", amigosDTO.get(0).getUsername());
    }

    @Test
    public void testProponerActividad() {
        ActividadDTO actividadDTO = new ActividadDTO();
        actividadDTO.setNombre("ActividadTest");
        actividadDTO.setDescripcion("Descripción");
        actividadDTO.setTipoActividad("Tipo");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo));

        Propuesta propuestaCreada = usuarioService.proponerActividad(1L, actividadDTO);

        assertNotNull(propuestaCreada);
        assertEquals("Propuesta de actividad por el usuario UsuarioTest", propuestaCreada.getDescripcion());
        assertEquals(usuario, propuestaCreada.getCreador());
        assertEquals(grupo, propuestaCreada.getGrupo());
    }

    @Test
    public void testVotarActividad() {
        when(actividadRepository.findById(1L)).thenReturn(Optional.of(actividad));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(propuestaRepository.findByActividad(actividad)).thenReturn(Optional.of(propuesta));

        VotoDTO votoDTO = usuarioService.votarActividad(1L, 1L, true);

        assertNotNull(votoDTO);
        assertEquals(1L, votoDTO.getVotoId());
        assertTrue(votoDTO.isVotoAFavor());
    }

    @Test
    public void testVerActividades() {
        List<Propuesta> propuestas = Arrays.asList(propuesta);
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo));
        when(propuestaRepository.findByGrupo(grupo)).thenReturn(propuestas);

        List<ActividadConVotosDTO> actividadesDTO = usuarioService.verActividades(1L);

        assertNotNull(actividadesDTO);
        assertEquals(1, actividadesDTO.size());
        assertEquals(actividad.getNombre(), actividadesDTO.get(0).getTipoActividad());
    }

    @Test
    public void testValidarCredenciales() {
        when(usuarioRepository.findByUsername("UsuarioTest")).thenReturn(usuario);

        Usuario usuarioValidado = usuarioService.validarCredenciales("UsuarioTest", "password");

        assertNotNull(usuarioValidado);
        assertEquals("UsuarioTest", usuarioValidado.getUsername());

        Usuario usuarioInvalidado = usuarioService.validarCredenciales("UsuarioTest", "wrongpassword");
        assertNull(usuarioInvalidado);
    }
}

