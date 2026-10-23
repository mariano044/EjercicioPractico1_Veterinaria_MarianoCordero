package com.clinicaveterinaria.citas.controllers;

import com.clinicaveterinaria.citas.domain.ConsultaContacto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {

    @ModelAttribute("seccion")
    public String seccionActiva() {
        return "inicio";
    }

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("consultaContacto", new ConsultaContacto());
        return "index";
    }

    @PostMapping("/consultas/enviar")
    public String recibirConsulta(@ModelAttribute ConsultaContacto consultaContacto,
                                  RedirectAttributes atributos) {
        // Solo interfaz: no se guarda en base de datos
        atributos.addFlashAttribute("mensajeExito",
                "¡Gracias, " + consultaContacto.getNombreCliente()
                        + "! Recibimos tu consulta sobre " + consultaContacto.getNombreMascota()
                        + " y te responderemos pronto.");
        return "redirect:/#consultas";
    }
}
