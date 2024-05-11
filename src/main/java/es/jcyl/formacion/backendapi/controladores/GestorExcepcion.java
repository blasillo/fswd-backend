package es.jcyl.formacion.backendapi.controladores;


import es.jcyl.formacion.backendapi.excepciones.RespuestaExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GestorExcepcion {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaExcepcion> gestor (Exception exp) {
        exp.printStackTrace();

        return  ResponseEntity.status( BAD_REQUEST )
                .body(
                        RespuestaExcepcion.builder()
                                .codigoError(404)
                                .descripcionError( "Error en la aplicacion" )
                                .error( exp.getMessage() )
                                .build()
                );
    }
}
