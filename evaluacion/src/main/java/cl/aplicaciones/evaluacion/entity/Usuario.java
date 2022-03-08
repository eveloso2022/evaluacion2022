package cl.aplicaciones.evaluacion.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
public  class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator= "usuario_generator")
    @Getter
    @Setter
    private UUID id;

    @NotNull
    @Getter
    @Setter
    private String password;

    @NotNull
    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @NotBlank
    @Getter
    @Setter
    private String username;

    @OneToMany
    @Fetch(value = FetchMode.SELECT)
    @Getter
    @JoinColumn(name = "usuario_id") // we need to duplicate the physical information
    private Set<Telefono> telefono;
}
