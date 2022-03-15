package cl.aplicaciones.evaluacion.rest;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import cl.aplicaciones.evaluacion.util.ValidadorPasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@Slf4j
@Validated
public class UsuariosRestController {

    @Autowired
    IUsuarioService usuarioServices;

    @PostMapping(value = "/inyectarUsuario",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> inyectarUsuario(@Valid @RequestBody UsuarioDto usuario) {
        ValidadorPasswordUtil validadorPasswordUtil = new ValidadorPasswordUtil();
        if (!validadorPasswordUtil.validarPassword(usuario.getPassword())) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje","Password no valido");
            return new ResponseEntity(body,null, HttpStatus.BAD_REQUEST);
        } else {
            return usuarioServices.inyectarUsuario( usuario);
        }
    }
}
