package com.clinicaveterinaria.citas.repository;

import com.clinicaveterinaria.citas.domain.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {
}
