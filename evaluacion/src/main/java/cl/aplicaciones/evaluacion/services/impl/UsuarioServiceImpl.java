package cl.aplicaciones.evaluacion.services.impl;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.entity.Telefono;
import cl.aplicaciones.evaluacion.entity.Usuario;
import cl.aplicaciones.evaluacion.repository.TelefonoRepository;
import cl.aplicaciones.evaluacion.repository.UsuarioRepository;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelefonoRepository telefonoRepository;

    public UsuarioDto inyectarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getName());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        log.debug("Antes de buscar por email");
        Usuario usuarioPorEmail = usuarioRepository.findUsuarioByEmail(usuarioDto.getEmail());

        log.debug("ya buscamos por email");
        if ( usuarioPorEmail == null ) {
            log.debug("guardando usuario");
            Usuario usuarioEntityBD = usuarioRepository.save(usuario);
            log.debug("guardando telefonos");
            Arrays.stream(usuarioDto.getPhones())
                    .map(a -> new Telefono(
                            a.getNumber(),
                            a.getCityCode(),
                            a.getCountryCode(),
                            usuarioEntityBD))
                    .collect(Collectors.toList())
                    .forEach(t -> telefonoRepository.save(t));
            log.debug("buscando registros");
        }
        else {
            log.debug("Antes del if");
            if (usuarioDto.getPhones() != null && usuarioDto.getPhones().length > 0){
                log.debug("Antes de borrar por id");
                telefonoRepository.deleteAll(usuarioPorEmail.getTelefono());
                Arrays.stream(usuarioDto.getPhones())
                        .map(a -> new Telefono(
                                a.getNumber(),
                                a.getCityCode(),
                                a.getCountryCode(),
                                usuarioPorEmail))
                        .collect(Collectors.toList())
                        .forEach(t -> telefonoRepository.save(t));
            }
        }
        log.info("Creado en Base de datos:" + usuarioPorEmail);
        return usuarioDto;
    }
}
