package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepositorio extends JpaRepository<Rol,Integer> {


    // TODO : consultar todos los usuarios de un rol
    List<Usuario> listadoUsuariosPorRol (String nombreRol);
}
