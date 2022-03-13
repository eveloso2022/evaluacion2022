package cl.aplicaciones.evaluacion.rest;

import cl.aplicaciones.evaluacion.dto.TelefonoDto;
import cl.aplicaciones.evaluacion.dto.UsuarioDto;
import cl.aplicaciones.evaluacion.repository.TelefonoRepository;
import cl.aplicaciones.evaluacion.repository.UsuarioRepository;
import cl.aplicaciones.evaluacion.services.IUsuarioService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UsuariosRestControllerTest {

    @Autowired
    IUsuarioService usuarioServices;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TelefonoRepository telefonoRepository;

    @Test
    void test1_inyectarUsuarioSinTelefono() {
        UsuarioDto usr = new UsuarioDto();
        usr.setName("nombre");
        usr.setPassword("password");
        usr.setEmail("aaaaaaa@dominio.cl");
        usr.setPhones(null);
        ResponseEntity response = usuarioServices.inyectarUsuario(usr);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void test3_inyectarUsuarioConTelefono() {
        UsuarioDto usr = new UsuarioDto();
        usr.setName("nombre2");
        usr.setPassword("password2");
        usr.setEmail("bbbbbbb@dominio.cl");
        TelefonoDto[] telefonos = new TelefonoDto[2];
        TelefonoDto tel1 = new TelefonoDto();
        tel1.setCityCode(1);
        tel1.setNumber("123123");
        tel1.setCountryCode(1212);
        telefonos[0] = tel1;
        TelefonoDto tel2 = new TelefonoDto();
        tel2.setCityCode(1);
        tel2.setNumber("123123");
        tel2.setCountryCode(1212);
        telefonos[1] = tel2;
        usr.setPhones(telefonos);
        ResponseEntity response = usuarioServices.inyectarUsuario(usr);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void test4_inyectarUsuarioYaRegistrado() {
        UsuarioDto usr = new UsuarioDto();
        usr.setName("nombre");
        usr.setPassword("password");
        usr.setEmail("aaaaaaa@dominio.cl");

        ResponseEntity response = usuarioServices.inyectarUsuario(usr);
        assertThat(response.getBody()).asString().contains("El correo ya registrado");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void test5_buscarUsuarioPorEmailNoRegistradoRepositorio() {
        assertNull(usuarioRepository.findUsuarioByEmail("xsxsxsxs@dominio.cl"));
    }

    @Test
    void test6_buscarUsuarioPorEmailRegistradoRepositorio() {
        assertNotNull(usuarioRepository.findUsuarioByEmail("aaaaaaa@dominio.cl"));
    }

}