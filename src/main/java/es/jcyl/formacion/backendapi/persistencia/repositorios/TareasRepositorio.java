package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepositorio extends JpaRepository<Tarea,Integer> {
}
