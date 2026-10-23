package com.clinicaveterinaria.citas.service;

import com.clinicaveterinaria.citas.domain.TipoServicio;
import com.clinicaveterinaria.citas.repository.TipoServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    public TipoServicioServiceImpl(TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> listarTodos() {
        List<TipoServicio> tipos = tipoServicioRepository.findAll();
        // Inicializa la colección para poder contar los servicios asociados
        tipos.forEach(tipo -> tipo.getServiciosAsociados().size());
        return tipos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarPorId(Integer id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    @Transactional
    public TipoServicio registrar(TipoServicio tipoServicio) {
        return tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        tipoServicioRepository.deleteById(id);
        tipoServicioRepository.flush();
    }
}
