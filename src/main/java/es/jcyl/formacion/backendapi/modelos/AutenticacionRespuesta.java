package es.jcyl.formacion.backendapi.modelos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AutenticacionRespuesta {
   private String token;
}
