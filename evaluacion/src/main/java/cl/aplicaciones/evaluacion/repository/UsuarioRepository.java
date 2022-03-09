package cl.aplicaciones.evaluacion.repository;

import cl.aplicaciones.evaluacion.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    public Usuario findUsuarioByEmail(@Param("email") String email);


}
