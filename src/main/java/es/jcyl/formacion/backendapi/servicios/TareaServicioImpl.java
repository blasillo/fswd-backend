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

// anotación requerida
@Service
// anotaciones necesarios
@RequiredArgsConstructor
public class TareaServicioImpl implements TareaServicio {

    // inyectar dependecias
    // @Autowired
    TareasRepositorio tareasRepo;
    // @Autowired
    UsuariosRepositorio usuariosRepo;

    // inyectar dependencia
    // @Autowired
    TareaMapeo mapeo;


    @Override
    public TareaModelo crearTarea(TareaModelo tarea) {
        Usuario usuario = usuariosRepo.findByCorreo( tarea.getUsuarioCorreo() )
                .orElseThrow( () ->  new EntityNotFoundException("El usuario no existe"));

        Tarea nueva =  tareasRepo.save (  mapeo.deModeloAEntidad( tarea , usuario )  );
        return mapeo.deEntidadAModelo( nueva ) ;
    }

    @Override
    public List<TareaModelo> obtenerTareas(String correo) {
        // recuperar usuario a partir del correo
        Usuario usuario = usuariosRepo.findByCorreo(correo).orElseThrow(() ->  new EntityNotFoundException("El usuario no existe"));

        List<Tarea> tareas = tareasRepo.findByUsuario(usuario);// recuperar la lista de tareas

        List<TareaModelo> respuesta; //  convertir la lista de entidades en lista de DTO
        respuesta = tareas.stream().map(mapeo::deEntidadAModelo).toList();

        return respuesta;
    }

    @Override
    public TareaModelo modificarTarea(TareaModelo modelo) {
        Optional<Tarea> tarea = tareasRepo.findById(modelo.getId());
        if(tarea.isEmpty()) {
            throw new EntityNotFoundException("La tarea no existe");
        }

        String email = modelo.getUsuarioCorreo();

        Usuario usuario = usuariosRepo.findByCorreo(email)
                .orElseThrow( () ->  new EntityNotFoundException("El usuario no existe"));

        return mapeo.deEntidadAModelo( tareasRepo.save( mapeo.deModeloAEntidad (modelo,usuario) ) );
    }

    @Override
    public Integer borrarTarea(Integer tareaId) {
        Optional<Tarea> tarea = tareasRepo.findById(tareaId);

        // si la tarea existe borrarla
        if(tarea.isEmpty()) {
            throw new EntityNotFoundException("La tarea no existe");
        }
        tarea.ifPresent(tareasRepo::delete);

        return tareaId;
    }
}
