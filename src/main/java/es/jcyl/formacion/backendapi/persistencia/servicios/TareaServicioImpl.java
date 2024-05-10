package es.jcyl.formacion.backendapi.persistencia.servicios;


import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import es.jcyl.formacion.backendapi.persistencia.repositorios.TareasRepositorio;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return List.of();
    }

    @Override
    public TareaModelo modificarTarea(TareaModelo tarea) {
        return null;
    }

    @Override
    public Integer borrarTarea(Integer tareaId) {
        return 0;
    }
}
