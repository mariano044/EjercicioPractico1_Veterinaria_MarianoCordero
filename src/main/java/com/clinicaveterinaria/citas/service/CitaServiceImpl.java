package com.clinicaveterinaria.citas.service;

import com.clinicaveterinaria.citas.domain.Cita;
import com.clinicaveterinaria.citas.repository.CitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarProgramadas() {
        List<Cita> citas = citaRepository.findAllByOrderByFechaAsc();
        citas.forEach(cita -> cita.getServicio().getNombre());
        return citas;
    }
}
