package com.clinicaveterinaria.citas.controllers;

import com.clinicaveterinaria.citas.domain.TipoServicio;
import com.clinicaveterinaria.citas.service.TipoServicioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @ModelAttribute("seccion")
    public String seccionActiva() {
        return "tipos";
    }

    @GetMapping
    public String mostrarListado(Model model) {
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id,
                                           Model model,
                                           RedirectAttributes atributos) {
        return tipoServicioService.buscarPorId(id)
                .map(tipo -> {
                    model.addAttribute("tipoServicio", tipo);
                    return "tipos/formulario";
                })
                .orElseGet(() -> {
                    atributos.addFlashAttribute("mensajeError", "El tipo de servicio no existe.");
                    return "redirect:/tipos";
                });
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute TipoServicio tipoServicio,
                              RedirectAttributes atributos) {
        boolean esNuevo = tipoServicio.getId() == null;
        tipoServicioService.registrar(tipoServicio);
        atributos.addFlashAttribute("mensajeExito", esNuevo
                ? "Tipo de servicio agregado correctamente."
                : "Tipo de servicio modificado correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar")
    public String eliminarTipo(@RequestParam Integer id, RedirectAttributes atributos) {
        try {
            tipoServicioService.borrarPorId(id);
            atributos.addFlashAttribute("mensajeExito", "Tipo de servicio eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            atributos.addFlashAttribute("mensajeError",
                    "No se puede eliminar: el tipo tiene servicios asociados.");
        } catch (RuntimeException ex) {
            atributos.addFlashAttribute("mensajeError", "No se pudo eliminar el tipo de servicio.");
        }
        return "redirect:/tipos";
    }
}
