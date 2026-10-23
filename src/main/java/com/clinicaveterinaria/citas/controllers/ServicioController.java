package com.clinicaveterinaria.citas.controllers;

import com.clinicaveterinaria.citas.domain.Servicio;
import com.clinicaveterinaria.citas.domain.TipoServicio;
import com.clinicaveterinaria.citas.service.ServicioService;
import com.clinicaveterinaria.citas.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    public ServicioController(ServicioService servicioService,
                              TipoServicioService tipoServicioService) {
        this.servicioService = servicioService;
        this.tipoServicioService = tipoServicioService;
    }

    @ModelAttribute("seccion")
    public String seccionActiva() {
        return "servicios";
    }

    @GetMapping
    public String mostrarListado(Model model) {
        model.addAttribute("listaServicios", servicioService.listarTodos());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Servicio servicio = new Servicio();
        servicio.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", servicio);
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id,
                                           Model model,
                                           RedirectAttributes atributos) {
        return servicioService.buscarPorId(id)
                .map(servicio -> {
                    model.addAttribute("servicio", servicio);
                    model.addAttribute("listaTipos", tipoServicioService.listarTodos());
                    return "servicios/formulario";
                })
                .orElseGet(() -> {
                    atributos.addFlashAttribute("mensajeError", "El servicio no existe.");
                    return "redirect:/servicios";
                });
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio,
                                  RedirectAttributes atributos) {
        boolean esNuevo = servicio.getId() == null;
        try {
            servicioService.registrar(servicio);
            atributos.addFlashAttribute("mensajeExito", esNuevo
                    ? "Servicio agregado correctamente."
                    : "Servicio modificado correctamente.");
        } catch (IllegalArgumentException ex) {
            atributos.addFlashAttribute("mensajeError", ex.getMessage());
        }
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar")
    public String eliminarServicio(@RequestParam Integer id, RedirectAttributes atributos) {
        try {
            servicioService.borrarPorId(id);
            atributos.addFlashAttribute("mensajeExito", "Servicio eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            atributos.addFlashAttribute("mensajeError",
                    "No se puede eliminar: el servicio tiene citas programadas.");
        } catch (RuntimeException ex) {
            atributos.addFlashAttribute("mensajeError", "No se pudo eliminar el servicio.");
        }
        return "redirect:/servicios";
    }
}
