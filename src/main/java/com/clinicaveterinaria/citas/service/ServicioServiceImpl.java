package com.clinicaveterinaria.citas.service;

import com.clinicaveterinaria.citas.domain.Servicio;
import com.clinicaveterinaria.citas.domain.TipoServicio;
import com.clinicaveterinaria.citas.repository.ServicioRepository;
import com.clinicaveterinaria.citas.repository.TipoServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final TipoServicioRepository tipoServicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository,
                               TipoServicioRepository tipoServicioRepository) {
        this.servicioRepository = servicioRepository;
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listarTodos() {
        List<Servicio> servicios = servicioRepository.findAll();
        // Inicializa el tipo para mostrarlo en el listado
        servicios.forEach(servicio -> servicio.getTipoServicio().getNombre());
        return servicios;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> buscarPorId(Integer id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        servicio.ifPresent(s -> s.getTipoServicio().getNombre());
        return servicio;
    }

    @Override
    @Transactional
    public Servicio registrar(Servicio servicio) {
        Integer idTipo = servicio.getTipoServicio() == null ? null : servicio.getTipoServicio().getId();
        if (idTipo == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de servicio");
        }
        TipoServicio tipoExistente = tipoServicioRepository.findById(idTipo)
                .orElseThrow(() -> new IllegalArgumentException("El tipo de servicio no existe"));
        servicio.setTipoServicio(tipoExistente);
        return servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void borrarPorId(Integer id) {
        servicioRepository.deleteById(id);
        servicioRepository.flush();
    }
}
