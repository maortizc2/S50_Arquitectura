package com.eafit.nutrition.repository;

import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.model.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public  interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findByEmail(String email);
    Optional<Nutricionista> findByNumeroLicencia(String numeroLicencia);

    // join fetch
    @Query("SELECT n FROM Nutricionista n LEFT JOIN FETCH n.pacientes WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientes(@Param("id") Long id);

    // Utiliza proyecciones para recuperar solo los datos necesarios:
    public interface PacienteResumen {
        Long getId();
        String getNombre();
        String getApellido();
    }

    @Query("SELECT p.id as id, p.nombre as nombre, p.apellido as apellido FROM Paciente p WHERE p.nutricionista.id = :nutricionistaId")
    List<PacienteResumen> findPacienteResumenByNutricionistaId(@Param("nutricionistaId") Long nutricionistaId);
}
