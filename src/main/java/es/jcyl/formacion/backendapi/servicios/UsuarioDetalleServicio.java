package es.jcyl.formacion.backendapi.servicios;

import es.jcyl.formacion.backendapi.persistencia.repositorios.UsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetalleServicio implements UserDetailsService {

    private final UsuariosRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return repositorio.findByCorreo( correo )
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
