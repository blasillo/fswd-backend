package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepositorio extends JpaRepository<Rol,Integer> {
}
