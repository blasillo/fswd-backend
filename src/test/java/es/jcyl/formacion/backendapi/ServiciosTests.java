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

    // TODO: inyectar
    private TareasRepositorio tareasRepo;

    // TODO: inyectar
    private UsuariosRepositorio usuariosRepo;

    // TODO: inyectar
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

        Usuario result = usuariosRepo.findByCorreo("fede.valverde@fmail.com")
                .orElse(null);

        // TODO: se crea la tarea
        // TODO: el correo corresponde al usuario

        TareaModelo miTarea = TareaModelo.builder()
                .nombre("Primera Tarea")
                .estado(0)
                .color("green-500")
                .usuarioCorreo("fede.valverde@fmail.com")
                .build();

        TareaModelo tareaResult = servicio.crearTarea( miTarea );

        // TODO: se ha creado la tarea
        //  TODO: tiene el mismo nombre que se ha asignado
        // TODO: se le asignado un id
        // TODO: pertenece al usuario

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

        // TODO: se obtine una lista
        // TODO: la lista tiene un registro
    }

    @Test
    @Order(3)
    public void deberiaLanzarExcepcionUsuarioNoExiste() {
        String correo = "no.existe@fmail.com";

        //TODO: lanza EntityNotFoundException.class cuando servicio.obtenerTareas( correo )
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

        // TODO: la tarea existe
        // TODO: conserva el mismo id
        // TODO: conserva el mismo nombre
        // TODO: se ha actualizado el campo

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

        // TODO: la tarea existe

        Integer id = tareaResult.getId();

        servicio.borrarTarea( id );

        Optional<Tarea> result = tareasRepo.findById( id );
        // TODO: comprobar que se ha borrado

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

        // TODO: comprobar que el correo es invalido

    }






}
