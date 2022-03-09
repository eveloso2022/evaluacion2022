package cl.aplicaciones.evaluacion.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
public class UsuarioDto {

    @NotNull(message = "Debe informar name - null")
    @NotEmpty(message = "Debe informar name - vacio")
    private String name;

    @NotNull(message = "Debe informar email - null")
    @NotEmpty(message = "Debe informar email - vacio")
    private String email;

    @NotNull(message = "Debe informar password - null")
    @NotEmpty(message = "Debe informar password - vacio")
    private String password;

    private TelefonoDto[] phones;

}
