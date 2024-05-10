package es.jcyl.formacion.backendapi.servicios;


import es.jcyl.formacion.backendapi.modelos.TareaModelo;
import es.jcyl.formacion.backendapi.persistencia.entidades.Tarea;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;
import org.springframework.stereotype.Service;

@Service
public class TareaMapeo {

    public Tarea deModeloAEntidad (TareaModelo modelo, Usuario usuario) {

        return Tarea.builder()
                .id ( modelo.getId())
                .nombre( modelo.getNombre())
                .estado( modelo.getEstado())
                .color(modelo.getColor())
                .usuario( usuario )
                .build();
    }

    public TareaModelo deEntidadAModelo (Tarea tarea) {

        return TareaModelo.builder()
                .id ( tarea.getId())
                .nombre( tarea.getNombre())
                .estado( tarea.getEstado())
                .color( tarea.getColor())
                .usuarioCorreo( tarea.getUsuario().getCorreo())
                .build();
    }
 }
