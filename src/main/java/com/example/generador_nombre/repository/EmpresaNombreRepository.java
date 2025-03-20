package com.example.generador_nombre.repository;

import com.example.generador_nombre.model.EmpresaNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaNombreRepository extends JpaRepository<EmpresaNombre, Long> {
}
