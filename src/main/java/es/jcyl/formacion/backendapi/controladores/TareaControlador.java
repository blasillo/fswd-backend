package es.jcyl.formacion.backendapi.controladores;


import es.jcyl.formacion.backendapi.servicios.TareaServicio;
import es.jcyl.formacion.backendapi.modelos.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//TODO CORS
@RequestMapping("tareas")
@RequiredArgsConstructor
public class TareaControlador {

    private final TareaServicio servicio;


    @PostMapping
    public ResponseEntity<TareaModelo>  nuevaTarea (
            @Valid @RequestBody TareaModelo modelo) {
        return ResponseEntity.ok(  servicio.crearTarea ( modelo) );
    }

    @GetMapping
    public ResponseEntity<List<TareaModelo>> listadoTareas (
            @RequestParam("correo") String correo) {
        return ResponseEntity.ok ( servicio.obtenerTareas(correo));
    }

    @PutMapping()
    public ResponseEntity<TareaModelo> editarTarea (
            @Valid @RequestBody TareaModelo modelo) {
        return ResponseEntity.ok( servicio.modificarTarea( modelo ));
    }

    @DeleteMapping()
    public Integer deleteTodo(@RequestParam("tareaId") Integer id) {
        return servicio.borrarTarea( id );
    }

}
