package es.jcyl.formacion.backendapi;

import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.RolesRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import es.jcyl.formacion.backendapi.servicios.TareaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
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
            usuarioRepo.save (
                    Usuario.builder()
                            .nombreCompleto("Usuario demo 2")
                            .correo("pei@eclap.jcyl.es")
                            .clave("CambiaLaClaveYa!")
                            .roles( Arrays.asList(base, base ))
                            .build()
            );

            // consultar

            List<Usuario> admins =  usuarioRepo.listadoAdministradores();//usuarioRepo.findByRolesIs( admin );
            admins.forEach( usu -> log.info ("Administrador: {} ", usu.getNombreCompleto() ));

            rolRepo.listadoUsuariosPorRol( "BASE").forEach( u -> log.info("Usuario base: {} " , u.getNombreCompleto() ));


            // crear tarea

            TareaModelo modelo = TareaModelo.builder()
                                            .nombre("Demo")
                                            .estado(0)
                                            .color("ROJO")
                                            .usuarioCorreo("formacion@eclap.jcyl.es").build();

            TareaModelo resultado = tareaSrv.crearTarea( modelo );

            TareaModelo modelo3 = TareaModelo.builder()
                    .nombre("Demo 3")
                    .estado(20)
                    .color("VERDE")
                    .usuarioCorreo("pei@eclap.jcyl.es").build();

            TareaModelo resultado3 = tareaSrv.crearTarea( modelo3 );

            log.info( "Tarea creada : {} por {} " ,resultado.getNombre() , resultado.getUsuarioCorreo() );
            log.info( "Tarea creada : {} por {} " ,resultado3.getNombre() , resultado3.getUsuarioCorreo() );

            List<TareaModelo> misTareas = tareaSrv.obtenerTareas( "formacion@eclap.jcyl.es" );

            misTareas.forEach( t -> log.info("Mi Tarea : {} por {} " ,t.getNombre() , t.getUsuarioCorreo()  ));

            resultado.setNombre ("Demo terminada");

            TareaModelo resultado2 = tareaSrv.modificarTarea( resultado );

            log.info( "Tarea modificada {} por {}: ", resultado2.getNombre() , resultado2.getUsuarioCorreo() );

            tareaSrv.borrarTarea(resultado2.getId());

            misTareas = tareaSrv.obtenerTareas( "formacion@eclap.jcyl.es" );
            log.info ("Contador Tareas: {}", misTareas.size() );

        };
    }

}
