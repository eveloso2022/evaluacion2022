package cl.aplicaciones.evaluacion.services.impl;

import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.entity.Telefono;
import cl.aplicaciones.evaluacion.entity.Usuario;
import cl.aplicaciones.evaluacion.repository.TelefonoRepository;
import cl.aplicaciones.evaluacion.repository.UsuarioRepository;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import cl.aplicaciones.evaluacion.util.TokenUtil;
import cl.aplicaciones.evaluacion.util.UsuarioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TelefonoRepository telefonoRepository;

    public UsuarioServiceImpl() {
    }

    public ResponseEntity inyectarUsuario(UsuarioDto user) {
        log.debug("usuarioPorEmail[" + (user == null ? "null" : user) + "] Inicio");
        Usuario usuario = new Usuario();
        log.debug("Buscando email[" + user.getEmail() + "]");
        Usuario usuarioPorEmail = usuarioRepository.findUsuarioByEmail(user.getEmail());
        log.debug("usuarioPorEmail[" + (usuarioPorEmail == null ? "null" : usuarioPorEmail) + "]");
        if ( usuarioPorEmail == null ) {

            TokenUtil tokenUtil = new TokenUtil();
            usuario.setUsername(user.getName());
            usuario.setEmail(user.getEmail());
            usuario.setPassword(user.getPassword());
            usuario.setCreated(Calendar.getInstance().getTime());
            usuario.setModified(Calendar.getInstance().getTime());
            usuario.setLast_login(Calendar.getInstance().getTime());
            usuario.setIsactive("true");
            usuario.setToken(tokenUtil.generateToken(user.getPassword(), user.getEmail()));

            Usuario usuarioEntityBD = usuarioRepository.save(usuario);

            if (user.getPhones() != null && user.getPhones().length > 0){
                Arrays.stream(user.getPhones())
                        .map(a -> new Telefono(
                                a.getNumber(),
                                a.getCityCode(),
                                a.getCountryCode(),
                                usuarioEntityBD))
                        .collect(Collectors.toList())
                        .forEach(t -> telefonoRepository.save(t));

            }

            UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setId(usuarioEntityBD.getId());
            usuarioResponse.setCreated(usuarioEntityBD.getCreated());
            usuarioResponse.setModified(usuarioEntityBD.getModified());
            usuarioResponse.setLast_login(usuarioEntityBD.getLast_login());
            usuarioResponse.setIsactive(usuarioEntityBD.getIsactive());
            usuarioResponse.setToken(usuarioEntityBD.getToken());

            return new ResponseEntity(usuarioResponse, HttpStatus.CREATED);
        }
        else {
            log.debug("Correo existe en Base de datos");
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("mensaje", "El correo ya registrado");
            return new ResponseEntity(body, HttpStatus.BAD_REQUEST);

        }
    }
}
