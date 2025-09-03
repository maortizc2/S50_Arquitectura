package com.eafit.nutrition.controller;
import com.eafit.nutrition.model.Medicion;
import com.eafit.nutrition.service.MedicionServiceConstructor;
import com.eafit.nutrition.service.MedicionServiceAutowired;
import com.eafit.nutrition.service.MedicionServiceSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {
    private final MedicionServiceConstructor constructorService;

    @Autowired
    private MedicionServiceAutowired autowiredService;

    private MedicionServiceSetter setterService;

    public MedicionController(MedicionServiceConstructor constructorService) {
        this.constructorService = constructorService;
    }

    @Autowired
    public void setSetterService(MedicionServiceSetter setterService) {
        this.setterService = setterService;
    }

    @GetMapping("/constructor")
    public ResponseEntity<List<Medicion>> getAllMedicionesConstructor() {
        return ResponseEntity.ok(constructorService.findAll());
    }

    @GetMapping("/autowired")
    public ResponseEntity<List<Medicion>> getAllMedicionesAutowired() {
        return ResponseEntity.ok(autowiredService.findAll());
    }

    @GetMapping("/setter")
    public ResponseEntity<List<Medicion>> getAllMedicionesSetter() {
        return ResponseEntity.ok(setterService.findAll());
    }

    @GetMapping("/compare/{id}")
    public ResponseEntity<Map<String, Object>> compareMedicionById(@PathVariable Long id) {
        Optional<Medicion> constructorResult = constructorService.findById(id);
        Optional<Medicion> autowiredResult = autowiredService.findById(id);
        Optional<Medicion> setterResult = setterService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("constructorService", constructorResult.orElse(null));
        response.put("autowiredService", autowiredResult.orElse(null));
        response.put("setterService", setterResult.orElse(null));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/constructor/paciente/{pacienteId}/nutricionista/{nutricionistaId}")
    public ResponseEntity<Medicion> createMedicionConstructor(
            @PathVariable Long pacienteId,
            @PathVariable Long nutricionistaId,
            @RequestBody Medicion medicion) {
        Medicion createdMedicion = constructorService.createMedicion(pacienteId, nutricionistaId, medicion);
        return new ResponseEntity<>(createdMedicion, HttpStatus.CREATED);
    }

    @GetMapping("/compare/paciente/{pacienteId}/ultima-medicion")
    public ResponseEntity<Map<String, Object>> compareLastMedicion(@PathVariable Long pacienteId) {
        Optional<Medicion> constructorResult = constructorService.findLastMedicionByPacienteId(pacienteId);
        Optional<Medicion> autowiredResult = autowiredService.findLastMedicionByPacienteId(pacienteId);
        Optional<Medicion> setterResult = setterService.findLastMedicionByPacienteId(pacienteId);

        Map<String, Object> response = new HashMap<>();
        response.put("constructorService", constructorResult.orElse(null));
        response.put("autowiredService", autowiredResult.orElse(null));
        response.put("setterService", setterResult.orElse(null));

        return ResponseEntity.ok(response);
    }
}
