package com.clinicaveterinaria.citas.controllers;

import com.clinicaveterinaria.citas.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @ModelAttribute("seccion")
    public String seccionActiva() {
        return "citas";
    }

    @GetMapping
    public String mostrarCitas(Model model) {
        model.addAttribute("listaCitas", citaService.listarProgramadas());
        return "citas/lista";
    }
}
