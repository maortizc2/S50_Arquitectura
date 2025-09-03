package com.alejandra.referee.repository;

import com.alejandra.referee.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    
}
