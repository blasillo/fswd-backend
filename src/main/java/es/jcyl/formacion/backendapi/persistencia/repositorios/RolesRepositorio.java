package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepositorio extends JpaRepository<Rol,Integer> {

    @Query("""
            SELECT u 
              FROM Usuario u JOIN u.roles r 
              WHERE r.nombre = :nombreRol
            """)
    List<Usuario> listadoUsuariosPorRol (String nombreRol);
}
