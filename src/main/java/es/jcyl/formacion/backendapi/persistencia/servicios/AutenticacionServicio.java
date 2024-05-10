package es.jcyl.formacion.backendapi.persistencia.servicios;

import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;

//import org.springframework.security.core.Authentication;
//import java.util.Map;

public interface AutenticacionServicio {

    String registrarUsuario(Usuario nuevoUsuario);

    //Map<String, String> forgotPassword(Authentication authentication, Map<String, String> forgotter);

    //String reiniciarClace (Map<String, String> resetModel);

    //Map<String, String> inicioSesion(Authentication authentication, Map<String, String> loginModel);
}
