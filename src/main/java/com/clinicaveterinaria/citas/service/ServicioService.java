package com.clinicaveterinaria.citas.service;

import com.clinicaveterinaria.citas.domain.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> listarTodos();

    Optional<Servicio> buscarPorId(Integer id);

    Servicio registrar(Servicio servicio);

    void borrarPorId(Integer id);
}
