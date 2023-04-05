package com.Etrial.controller;

import com.Etrial.domain.Entrada;
import com.Etrial.service.EntradaService;
import com.Etrial.service.EventoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class EntradaController {

    @Autowired
    EntradaService entradaService;

    @Autowired
    EventoService eventoService;

    @GetMapping("/entrada/listado")
    public String inicio(Model model) {
        var entrada = entradaService.getEntradas(false);
        model.addAttribute("listaEntradas", entrada);
        return "/entrada/listado";
    }

    @GetMapping("/entrada/nuevo")
    public String nuevoEntrada(Entrada entrada, Model model) {
        var eventos = eventoService.getEventos(true);
        model.addAttribute("eventos", eventos);
        return "/entrada/modificar";
    }

    @PostMapping("/entrada/guardar")
    public String guardarEntrada(Entrada entrada) {
        entradaService.saveEntrada(entrada);
        return "redirect:/entrada/listado";
    }

    @GetMapping("/entrada/modificar/{idEntrada}")
    public String modificarEntrada(Entrada entrada, Model model) {
        var eventos = eventoService.getEventos(true);
        model.addAttribute("eventos", eventos);
        
        entrada = entradaService.getEntrada(entrada);
        model.addAttribute("entrada", entrada);
        return "/entrada/modificar";
    }

    @GetMapping("/entrada/eliminar/{idEntrada}")
    public String eliminarEntrada(Entrada entrada) {
        entradaService.deleteEntrada(entrada);
        return "redirect:/entrada/listado";
    }

}
