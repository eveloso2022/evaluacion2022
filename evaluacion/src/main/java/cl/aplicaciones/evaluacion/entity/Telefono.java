package cl.aplicaciones.evaluacion.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
public class Telefono implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator= "telefono_generator")
    private UUID id;

    @Setter
    @Getter
    private String number;

    @Setter
    @Getter
    private Integer cityCode;

    @Setter
    @Getter
    private Integer countryCode;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Telefono(String number, Integer cityCode, Integer countryCode, Usuario usuario) {
        this.number=number;
        this.cityCode=cityCode;
        this.countryCode=countryCode;
        this.usuario = usuario;
    }
}
