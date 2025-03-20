package com.example.generador_nombre.controller;

import com.example.generador_nombre.model.EmpresaNombre;
import com.example.generador_nombre.service.GeneradorNombreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nombres")  // <-- CORREGIDO
@CrossOrigin(origins = "*") //permite que el front acceda al back
public class GeneradorNombreController {
    
    @Autowired
    private GeneradorNombreService generadorNombreService;

    @PostMapping("/generar")
    public List<String> generarNombres(@RequestBody List<String> palabrasClave) {
        return generadorNombreService.generarNombres(palabrasClave.toArray(new String[0])); // Convertir List a Array si es necesario
    }

    @GetMapping("/historial")
    public List<EmpresaNombre> obtenerHistorial() {
        return generadorNombreService.obtenerHistorial(); // Método corregido
    }
}