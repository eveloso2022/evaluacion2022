package cl.aplicaciones.evaluacion.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@RequiredArgsConstructor
@Data
public class UsuarioDto {

    @NotBlank(message = "Ingrese un name (vacío)")
    private String name;

    @Pattern(message = "Ingrese un email (valido)" , regexp = "\\w[a-zA-Z]*@\\w[a-zA-Z]*.cl")
    private String email;

    @NotBlank(message = "Ingrese un password (vacío)")
    private String password;

    private TelefonoDto[] phones;

}
