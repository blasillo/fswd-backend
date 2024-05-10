package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<Usuario,Integer> {


    Usuario findByCorreo (String correo);

    Usuario findByCorreoAndClave(String correo, String clave);


    List<Usuario> findByRolesIs (Rol role);


    @Query ("""
       select u
        from Usuario u JOIN u.roles r 
       WHERE r.nombre = 'ADMINISTRADOR'       
    """)
    List<Usuario> listadoAdministradores ();



}
