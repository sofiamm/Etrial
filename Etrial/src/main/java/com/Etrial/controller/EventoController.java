package com.Etrial.controller;

import com.Etrial.domain.Evento;
import com.Etrial.service.EventoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class EventoController {
    
    @Autowired
    EventoService eventoService;

    @GetMapping("/evento/listado")
    public String inicio(Model model) {
        var eventos = eventoService.getEventos(false);
        model.addAttribute("listaEventos", eventos);
        return "/evento/listado";
    }

    @GetMapping("/evento/nueva")
    public String nuevoEvento(Evento evento) {
        return "/evento/modificar";
    }

    @PostMapping("/evento/guardar")
    public String guardarEvento(Evento evento) {
        eventoService.saveEvento(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/evento/modificar/{idEvento}")
    public String modificarEvento(Evento evento, Model model) {
        evento = eventoService.getEvento(evento);
        model.addAttribute("evento", evento);
        return "/evento/modificar";
    }

    @GetMapping("/evento/eliminar/{idEvento}")
    public String eliminarEvento(Evento evento) {
        eventoService.deleteEvento(evento);
        return "redirect:/evento/listado";
    }
    
}
