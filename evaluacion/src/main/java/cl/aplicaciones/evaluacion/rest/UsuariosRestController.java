package cl.aplicaciones.evaluacion.rest;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@Validated
public class UsuariosRestController {

    @Autowired
    IUsuarioService usuarioServices;

    @PostMapping(value = "/inyectarUsuario",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> inyectarUsuario(@Valid @RequestBody UsuarioDto usuario) {
        return usuarioServices.inyectarUsuario( usuario);
    }
}
