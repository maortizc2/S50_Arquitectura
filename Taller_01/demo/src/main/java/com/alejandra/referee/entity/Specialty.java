package com.alejandra.referee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Specialty {
    //== Attributes ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpecialty;

    @NotBlank
    @Size(max = 5000)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;

    // ===== Methods =====
    public boolean isCompatibleMatch(String matchCategory) {
        if (matchCategory == null || description == null) return false;
        return description.toLowerCase().contains(matchCategory.toLowerCase());
    }

    public String showInfo() {
    return name + " — " + description;
    }


    // ===== Getters/Setters =====
    public Long getIdSpecialty() { return idSpecialty; }
    public void setIdSpecialty(Long idSpecialty) { this.idSpecialty = idSpecialty; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

