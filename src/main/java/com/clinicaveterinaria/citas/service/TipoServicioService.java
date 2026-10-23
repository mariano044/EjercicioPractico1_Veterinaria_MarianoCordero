package com.clinicaveterinaria.citas.service;

import com.clinicaveterinaria.citas.domain.TipoServicio;

import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> listarTodos();

    Optional<TipoServicio> buscarPorId(Integer id);

    TipoServicio registrar(TipoServicio tipoServicio);

    void borrarPorId(Integer id);
}
