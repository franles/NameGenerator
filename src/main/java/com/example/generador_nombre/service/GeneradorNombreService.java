package com.example.generador_nombre.service;

import com.example.generador_nombre.model.EmpresaNombre;
import com.example.generador_nombre.repository.EmpresaNombreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service
public class GeneradorNombreService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final EmpresaNombreRepository repository;
    private final RestTemplate restTemplate;

    public GeneradorNombreService(EmpresaNombreRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<EmpresaNombre> obtenerHistorial() {
        return repository.findAll();  // Asumiendo que 'repository' es un JpaRepository que tiene el método findAll()
    }

    public List<String> generarNombres(String[] palabrasClave) {
        String prompt = "Genera 3 nombres creativos para una empresa o negocio basados en estas palabras clave: " + String.join(", ", palabrasClave);

        // Crear la petición a OpenAI
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> requestBody = Map.of(
            "model", "gpt-3.5-turbo",
            "messages", List.of(Map.of("role", "user", "content", prompt)),
            "max_tokens", 50
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud a OpenAI
        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);

        if (response.getBody() != null) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            List<String> nombresGenerados = choices.stream()
                .map(choice -> (String) ((Map<String, Object>) choice.get("message")).get("content"))
                .toList();
        
            // Guardar los nombres en la base de datos
            nombresGenerados.forEach(nombre -> repository.save(new EmpresaNombre(nombre)));
        
            return nombresGenerados;
        }

        return List.of();
    }
}