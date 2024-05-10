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

@Service
@RequiredArgsConstructor
public class TareaServicioImpl implements TareaServicio {

    private final TareasRepositorio tareasRepo;
    private final UsuariosRepositorio usuariosRepo;

    private final TareaMapeo mapeo;


    @Override
    public TareaModelo crearTarea(TareaModelo tarea) {
        Usuario usuario = usuariosRepo.findByCorreo( tarea.getUsuarioCorreo() );
        if(usuario == null) {
            throw new EntityNotFoundException("El usuario no existe");
        }
        Tarea almacenada =  tareasRepo.save (  mapeo.deModeloAEntidad( tarea , usuario )  );
        return mapeo.deEntidadAModelo( almacenada ) ;
    }

    @Override
    public List<TareaModelo> obtenerTareas(String email) {
        Usuario usuario = usuariosRepo.findByCorreo(email);
        if(usuario == null) {
            throw new EntityNotFoundException("El usuario no existe");
        }

        List<Tarea> tareas = tareasRepo.findByUsuario(usuario);
        List<TareaModelo> respuesta = tareas.stream().map(mapeo::deEntidadAModelo).toList();

        return respuesta;
    }

    @Override
    public TareaModelo modificarTarea(TareaModelo modelo) {
        Optional<Tarea> tarea = tareasRepo.findById(modelo.getId());
        if(tarea.isEmpty()) {
            throw new EntityNotFoundException("La tarea no existe");
        }
        Usuario usuario = usuariosRepo.findByCorreo(modelo.getUsuarioCorreo());
        if(usuario == null) {
            throw new EntityNotFoundException("El usuario no existe");
        }
        return mapeo.deEntidadAModelo( tareasRepo.save( mapeo.deModeloAEntidad (modelo,usuario) ) );
    }

    @Override
    public Integer borrarTarea(Integer tareaId) {
        Optional<Tarea> tarea = tareasRepo.findById(tareaId);

        if(tarea.isEmpty()) {
            throw new EntityNotFoundException("La tarea no existe");
        }
        tarea.ifPresent(tareasRepo::delete);
        return tareaId;
    }
}
