package es.jcyl.formacion.backendapi.persistencia.repositorios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareasRepositorio extends JpaRepository<Tarea,Integer> {

  // TODO: lista las tareas que pertenecen a un usuario
  List<Tarea> xxxxxxxxxxxxx (Usuario usuario);

  // TODO: lista de tareas cuyo estado esta por encima del 90%
  List<Tarea> xxxxxxxxxxxxxxxx ();



}
