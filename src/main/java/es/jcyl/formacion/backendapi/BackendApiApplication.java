package es.jcyl.formacion.backendapi;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.RolesRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner(RolesRepositorio rolRepo, UsuariosRepositorio usuarioRepo) {
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

        };
    }

}
