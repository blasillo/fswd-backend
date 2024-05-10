package es.jcyl.formacion.backendapi.servicios;

import es.jcyl.formacion.backendapi.modelos.Paginado;
import es.jcyl.formacion.backendapi.modelos.TareaModelo;

import java.util.List;

public interface TareaServicio {

    // definir CRUD
    TareaModelo crearTarea (TareaModelo tarea );
    List<TareaModelo> obtenerTareas ( String email );
    //Paginado<TareaModelo> obtenerTareasPaginado (String email);
    TareaModelo modificarTarea (TareaModelo tarea);
    Integer borrarTarea (Integer tareaId);
}


