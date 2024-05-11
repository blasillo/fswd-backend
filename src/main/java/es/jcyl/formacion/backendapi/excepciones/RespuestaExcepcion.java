package es.jcyl.formacion.backendapi.excepciones;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RespuestaExcepcion {
    private Integer codigoError;
    private String descripcionError;
    private String error;
    private Set<String> erroresValidation;
    private Map<String, String> errores;
}
