package es.jcyl.formacion.backendapi.controladores;


import es.jcyl.formacion.backendapi.excepciones.RespuestaExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GestorExcepcion {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaExcepcion> gestorNoValido (MethodArgumentNotValidException exp) {

        Set<String> errores = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errores.add(errorMessage);
                });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        RespuestaExcepcion.builder()
                                .erroresValidation( errores )
                                .build()
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaExcepcion> gestorExcepcion (Exception exp) {
        exp.printStackTrace();

        return  ResponseEntity.status( INTERNAL_SERVER_ERROR )
                .body(
                        RespuestaExcepcion.builder()
                                .codigoError(404)
                                .descripcionError( "Error en la aplicacion" )
                                .error( exp.getMessage() )
                                .build()
                );
    }
}
