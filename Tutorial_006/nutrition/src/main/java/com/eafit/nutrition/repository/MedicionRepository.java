package com.eafit.nutrition.repository;

import com.eafit.nutrition.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicionRepository extends JpaRepository<Medicion, Long> {
    List<Medicion> findByPacienteIdOrderByFechaDesc(Long pacienteId);
    Optional<Medicion> findFirstByPacienteIdOrderByFechaDesc(Long pacienteId);

     @Query(value = "SELECT * FROM medicion WHERE paciente_id = :pacienteId ORDER BY fecha DESC, id DESC LIMIT 1", nativeQuery = true)
    Optional<Medicion> findLastMedicionByPacienteId(@Param("pacienteId") Long pacienteId);
}
