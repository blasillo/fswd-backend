package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<Usuario,Integer> {


    //TODO: buscar un usuario por el correo
    Optional<Usuario> xxxxxx (String correo);

    //TODO: Buscar ususario por correo y clave
    Optional<Usuario> xxxxxx (String correo, String clave);

    //TODO: ver si un usuario con un correo existe
    Optional<Usuario> xxxxxxxxxx (String correo);

    //TODO: ver si un usuario tiene un rol
    List<Usuario> zzzzzzzzzzz (Rol role);


    // TODO: listado de los usuarios que tienen el rol de administrador
    List<Usuario> listadoAdministradores ();



}
