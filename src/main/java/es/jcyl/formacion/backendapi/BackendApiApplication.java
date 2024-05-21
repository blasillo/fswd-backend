package es.jcyl.formacion.backendapi;

import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.RolesRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import es.jcyl.formacion.backendapi.servicios.TareaServicio;
import jakarta.validation.Valid;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class BackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner(RolesRepositorio rolRepo,
                                    UsuariosRepositorio usuarioRepo,
                                    TareaServicio tareaSrv ) {
        return args -> {

            Rol base = rolRepo.save(Rol.builder().nombre("BASE").build());
            Rol admin = rolRepo.save(Rol.builder().nombre("ADMINISTRADOR").build());

            usuarioRepo.save (
                    Usuario.builder()
                            .nombreCompleto("Usuario demo")
                            .correo("formacion@eclap.jcyl.es")
                            .clave("CambiaLaClaveYa!")
                            .roles( Arrays.asList(base, admin ))
                            .build()
            );


            // consultar

            List<Usuario> admins =  usuarioRepo.listadoAdministradores();//usuarioRepo.findByRolesIs( admin );
            admins.forEach( usu -> {
                System.out.println ("Administrador: " + usu.getNombreCompleto() );
            });

            rolRepo.listadoUsuariosPorRol( "BASE").forEach( u -> { System.out.println ("Usuario base: " + u.getNombreCompleto() );});


            // crear tarea

            TareaModelo modelo = TareaModelo.builder()
                                            .nombre("Demo")
                                            .estado(0)
                                            .color("ROJO")
                                            .usuarioCorreo("formacion@eclap.jcyl.es").build();

            TareaModelo resultado = tareaSrv.crearTarea( modelo );

            System.out.println( "Tarea creada : " + resultado.getNombre() + " por " + resultado.getUsuarioCorreo() );

            List<TareaModelo> misTareas = tareaSrv.obtenerTareas( "formacion@eclap.jcyl.es" );

            misTareas.forEach( t -> { System.out.println ("Mi Tarea : " + t.getNombre() + " por " + t.getUsuarioCorreo()  ); } );

            resultado.setNombre ("Demo terminada");

            resultado.setUsuarioCorreo(""); //deberia dar error


            TareaModelo resultado2 = tareaSrv.modificarTarea( @Valid resultado );

            System.out.println( "Tarea modificada : " + resultado2.getNombre() + " por " + resultado2.getUsuarioCorreo() );

            /*
            tareaSrv.borrarTarea(resultado2.getId());

            misTareas = tareaSrv.obtenerTareas( "formacion@eclap.jcyl.es" );
            System.out.println ("Contador Tareas: " + misTareas.size() );
            */
        };
    }

}
