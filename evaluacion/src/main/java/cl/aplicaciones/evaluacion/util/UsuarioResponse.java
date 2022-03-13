package cl.aplicaciones.evaluacion.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
public class UsuarioResponse {

    @Setter
    private UUID id;

    @Setter
    private Date created;

    @Setter
    private Date modified;

    @Setter
    private Date last_login;

    @Setter
    private String token;

    @Setter
    private String isactive;

}
