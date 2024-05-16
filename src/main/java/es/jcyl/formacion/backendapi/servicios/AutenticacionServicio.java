package es.jcyl.formacion.backendapi.servicios;

import es.jcyl.formacion.backendapi.modelos.AutenticacionRespuesta;
import es.jcyl.formacion.backendapi.modelos.Credenciales;
import es.jcyl.formacion.backendapi.persistencia.entidades.Usuario;

import lombok.RequiredArgsConstructor;
import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AutenticacionServicio {

    private final UsuariosRepositorio usuarioRepo;
    private final JwtServicio jwtServicio;
    private final AuthenticationManager authManager;

    public AutenticacionRespuesta autenticar(Credenciales login) {

        var auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getCorreo(),
                        login.getClave()
                )
        );
        var claims = new HashMap<String, Object>();
        var usuario = ((Usuario) auth.getPrincipal());
        claims.put("email", usuario.getName());
        var jwtToken = jwtServicio.generateToken(claims,usuario);
        return AutenticacionRespuesta.builder()
                .token( jwtToken)
                .build();
    }


}
