package cl.aplicaciones.evaluacion.repository;

import  cl.aplicaciones.evaluacion.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, UUID> {

    @Modifying
    @Query(value = "DELETE TELEFONO WHERE USUARIO_ID = ?1 ", nativeQuery = true)
    public void deleteAllByUsuarioId(@Param("usuario_id") UUID id);
}
