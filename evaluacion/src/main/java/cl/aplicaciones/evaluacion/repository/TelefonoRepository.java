package cl.aplicaciones.evaluacion.repository;

import  cl.aplicaciones.evaluacion.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, UUID> {

}
