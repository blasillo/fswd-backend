package es.jcyl.formacion.backendapi;

import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.*;

import es.jcyl.formacion.backendapi.servicios.TareaMapeo;
import es.jcyl.formacion.backendapi.servicios.TareaServicio;
import es.jcyl.formacion.backendapi.servicios.TareaServicioImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ServiciosTests {

    @Autowired
    private TareasRepositorio tareasRepo;


    @Autowired
    private UsuariosRepositorio usuariosRepo;

    @Autowired
    private TareaMapeo mapeo;

    private TareaServicio servicio;


    private Usuario usuarioTest;


    @BeforeEach
    void setUp() {
        servicio = new TareaServicioImpl( tareasRepo,usuariosRepo,mapeo );

        if ( usuariosRepo.findByCorreo("fede.valverde@fmail.com").isEmpty()) {
            Usuario usuario = Usuario.builder()
                    .nombreCompleto("Fede Valverde")
                    .iniciales("FV")
                    .correo("fede.valverde@fmail.com")
                    .clave("Password1234#")
                    .build();
            usuarioTest = usuariosRepo.save(usuario);
        }

    }

    @Test
    @Order(1)
    public void deberiaCrearTarea() {

        Usuario result = usuariosRepo.findByCorreo("fede.valverde@fmail.com").orElse(null);

        assertNotNull(result);
        assertEquals("fede.valverde@fmail.com", result.getCorreo());

        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Primera Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fede.valverde@fmail.com")
                .build();

        TareaModelo tareaResult = servicio.crearTarea( miTarea );

        assertNotNull (tareaResult);
        assertEquals(miTarea.getNombre(), tareaResult.getNombre());
        assertNotNull( tareaResult.getId());
        assertEquals( tareaResult.getUsuarioCorreo() , miTarea.getUsuarioCorreo());

    }

    @Test
    @Order(2)
    public void deberiaObtenerListaTareas() {
        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Segunda Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fede.valverde@fmail.com")
                .build();

        TareaModelo tareaResult = servicio.crearTarea( miTarea );

        List<TareaModelo> listado = servicio.obtenerTareas("fede.valverde@fmail.com" );

        assertNotNull(listado);
        assertTrue(listado.size() >= 1);
    }

    @Test
    @Order(3)
    public void deberiaLanzarExcepcionUsuarioNoExiste() {
        String correo = "no.existe@fmail.com";

        EntityNotFoundException excepcion = assertThrows( EntityNotFoundException.class,
                () -> { servicio.obtenerTareas( correo ); });
    }



    @Test
    @Order(4)
    public void deberiaModificarTarea() {
        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Tercera Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fede.valverde@fmail.com")
                .build();

        TareaModelo tareaResult = servicio.crearTarea( miTarea );

        tareaResult.setEstado(90);

        TareaModelo tareaModif = servicio.modificarTarea( tareaResult );

        assertNotNull( tareaModif);
        assertEquals( tareaModif.getId(), tareaResult.getId());
        assertEquals( tareaModif.getNombre(), tareaResult.getNombre() );
        assertEquals( tareaModif.getEstado(), 90 );

    }

    @Test
    @Order(4)
    public void deberiaBorrarTarea() {

        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Cuarta Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fede.valverde@fmail.com")
                .build();

        TareaModelo tareaResult = servicio.crearTarea( miTarea );

        assertNotNull( tareaResult );
        Integer id = tareaResult.getId();

        servicio.borrarTarea( id );

        Optional<Tarea> result = tareasRepo.findById( id );
        assertNotNull(result);
        assertFalse(result.isPresent(), "El Optional debería estar vacío");

    }

    @Test
    @Order(5)
    public void deberiaValidarCorreo () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();

        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Cuarta Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fmail.com")
                .build();

        var violationes = validator.validate(miTarea);
        assertFalse(violationes.isEmpty(), "El correo electrónico debería ser inválido");

    }






}
