package es.jcyl.formacion.backendapi.servicios;


import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.TareasRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO : anotación requerida
// TODO : anotaciones necesarios
public class TareaServicioImpl implements TareaServicio {

    // TODO : inyectar dependecnias
    // TareasRepositorio tareasRepo;
    // UsuariosRepositorio usuariosRepo;

    // TODO: inyectar dependencia
    // TareaMapeo mapeo;


    @Override
    public TareaModelo crearTarea(TareaModelo tarea) {
        Usuario usuario = usuariosRepo.findByCorreo( tarea.getUsuarioCorreo() )
                .orElseThrow( () ->  new EntityNotFoundException("El usuario no existe"));

        Tarea nueva =  tareasRepo.save (  mapeo.deModeloAEntidad( tarea , usuario )  );
        return mapeo.deEntidadAModelo( nueva ) ;
    }

    @Override
    public List<TareaModelo> obtenerTareas(String correo) {
        // TODO: recuperar usuario a partir del correo

        List<Tarea> tareas = // TODO recuperar la lista de tareas

        List<TareaModelo> respuesta = // TODO convertir la lista de entidades en lista de DTO

        return respuesta;
    }

    @Override
    public TareaModelo modificarTarea(TareaModelo modelo) {
        Tarea tarea = tareasRepo.findById(modelo.getId());
        if(tarea.isEmpty()) {
            throw new EntityNotFoundException("La tarea no existe");
        }
        Usuario usuario = usuariosRepo.findByCorreo(email)
                .orElseThrow( () ->  new EntityNotFoundException("El usuario no existe"));

        return mapeo.deEntidadAModelo( tareasRepo.save( mapeo.deModeloAEntidad (modelo,usuario) ) );
    }

    @Override
    public Integer borrarTarea(Integer tareaId) {
        Optional<Tarea> tarea = tareasRepo.findById(tareaId).orElseThrow(
                () -> new EntityNotFoundException("La tarea no existe");
        );

        // TODO : si la tarea existe borrarla

        return tareaId;
    }
}
