package cl.aplicaciones.evaluacion.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
public  class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_generator")
    @Getter
    private UUID id;

    @NotNull
    @Setter
    private String password;

    @NotNull
    @Setter
    @Column(unique = true)
    private String email;

    @NotBlank
    @Setter
    private String username;

    @Getter
    @Setter
    private Date created;

    @Getter
    @Setter
    private Date modified;

    @Getter
    @Setter
    private Date last_login;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String isactive;

    @SuppressWarnings("unused")
    @OneToMany
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "usuario_id") // we need to duplicate the physical information
    private Set<Telefono> telefono;
}
