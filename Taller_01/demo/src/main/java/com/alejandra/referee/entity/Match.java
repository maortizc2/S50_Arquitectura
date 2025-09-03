package com.alejandra.referee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Match {
    //==Attributes==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;
    
    @NotBlank
    private String date; // Mantener como String para cumplir "solo primitivos" en el taller

    @NotBlank
    @Size(max = 150)
    private String location; // ubicación

    //== Methods ==
    public String sumaryMatch() {
    return "Match " + idMatch + " — Date: " + date + ", Location: " + location;
    }

    // Getters/Setters
    
    public Long getIdMatch() { return idMatch; }
public void setIdMatch(Long idMatch) { this.idMatch = idMatch; }
public String getDate() { return date; }
public void setDate(String date) { this.date = date; }
public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }
}
