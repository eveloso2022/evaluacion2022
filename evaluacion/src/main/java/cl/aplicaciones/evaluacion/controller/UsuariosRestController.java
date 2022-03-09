package cl.aplicaciones.evaluacion.controller;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import cl.aplicaciones.evaluacion.util.ResultadoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@Validated
public class UsuariosRestController {

    @Autowired
    IUsuarioService usuarioServices;

    @RequestMapping(value = "/inyectarUsuario",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultadoUtil<UsuarioDto> inyectarUsuario(@Valid @RequestBody UsuarioDto usuario) {
        UsuarioDto usuarioDto = usuarioServices.inyectarUsuario(usuario);
        log.debug(usuarioDto.toString());
        return new ResultadoUtil<UsuarioDto>(usuarioDto);
    }

    @GetMapping(value = "/crearUsuario1", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDto getPersonas3(@Valid @RequestBody UsuarioDto usuario) {
        log.debug("buscar por email: " +  usuario.toString());

        return usuario;
    }

}
