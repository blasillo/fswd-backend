package es.jcyl.formacion.backendapi.persistencia.servicios;

import es.jcyl.formacion.backendapi.modelos.TareaModelo;

import java.util.List;

public interface TareaServicio {

    // definir CRUD

    TareaModelo crearTarea (TareaModelo tarea );
    List<TareaModelo> obtenerTareas ( String email );
    TareaModelo modificarTarea (TareaModelo tarea);
    Integer borrarTarea (Integer tareaId);
}


