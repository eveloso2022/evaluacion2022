package cl.aplicaciones.evaluacion.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class TelefonoDto {

    @Positive(message = "Numero telefono debe ser mayor a 0")
    @NotEmpty(message = "Debe informar name - vacio")
    private String number;

    @Positive(message = "Codigo ciudad debe ser mayor a 0")
    private Integer cityCode;

    @Positive(message = "Codigo pais debe ser mayor a 0")
    private Integer countryCode;

}
