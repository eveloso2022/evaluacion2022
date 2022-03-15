package cl.aplicaciones.evaluacion.services;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {

    ResponseEntity inyectarUsuario(UsuarioDto usuarioDto);
}
