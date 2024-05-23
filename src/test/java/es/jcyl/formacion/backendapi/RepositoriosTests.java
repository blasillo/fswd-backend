package es.jcyl.formacion.backendapi;

import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.TareasRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RepositoriosTests {
    // TODO: inyectar
    private TareasRepositorio tareasRepo;

    // TODO: inyectar
    private UsuariosRepositorio usuariosRepo;



    @Test
    void deberiaEncontrarElUsario() {

        // Dado
        Usuario usuario = Usuario.builder()
                .nombreCompleto("Fede Valverde")
                .iniciales("FV")
                .correo("fede.valverde@fmail.com")
                .clave("Password1234#")
                .build();
        usuariosRepo.save( usuario );

        Tarea tarea = Tarea.builder()
                .nombre("Tarea Test")
                .estado(80)
                .color("#dddddd")
                .usuario(usuario)
                .build();
        tareasRepo.save( tarea );


        // cuando
        List<Tarea> lista = tareasRepo.findByUsuario(usuario);

        // entonces
        // TODO: comprobar que la lista existe y que tiene al menos 1 elemento

    }

    @Test
    void deberiaEncontrarPorCorreo() {
        // dado
        Usuario usuario = Usuario.builder()
                .nombreCompleto("Rogelio Navas")
                .iniciales("RN")
                .correo("rnavas@fmail.com")
                .clave("Password1234#")
                .build();
        usuario = usuariosRepo.save( usuario );

        // when
        Usuario recuperado = usuariosRepo.findByCorreo("rnavas@fmail.com").orElse(null);

        // TODO: comprobar que se ha recuperado un usuario

        // TODO: comprobar que es el mismo que se insertó originalmente

    }
}
