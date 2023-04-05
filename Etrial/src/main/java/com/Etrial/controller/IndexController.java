package com.Etrial.controller;

import com.Etrial.service.EntradaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    EntradaService entradaService;

    @GetMapping("/")
    public String inicio(Model model) {
        var entradas = entradaService.getEntradas(true);
        model.addAttribute("listaEntradas", entradas);
        return "index";
    }

}
