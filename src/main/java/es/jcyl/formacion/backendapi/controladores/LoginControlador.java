package es.jcyl.formacion.backendapi.controladores;

import es.jcyl.formacion.backendapi.modelos.AutenticacionRespuesta;
import es.jcyl.formacion.backendapi.modelos.Credenciales;
import es.jcyl.formacion.backendapi.servicios.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//TODO CORS
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginControlador {
    private final AutenticacionServicio servicio;

    @PostMapping("/autenticar")
    public ResponseEntity<AutenticacionRespuesta> autenticar (
            @RequestBody @Valid Credenciales login ) {

        return ResponseEntity.ok ( servicio.autenticar( login ));

    }




}
