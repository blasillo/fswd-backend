package es.jcyl.formacion.backendapi.configuracion;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info=@Info(
                description = "Aplicación del curso de DWFS",
                title = "OpenAPI Especificación",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Entorno local",
                        url = "http://localhost:8080/api/v1"
                )
        }
        // security
)
// @SecurityScheme( ... )
public class OpenApiConfig {
}
