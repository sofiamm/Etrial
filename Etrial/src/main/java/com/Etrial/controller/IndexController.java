package com.Etrial.controller;

import com.Etrial.dao.UsuarioDao;
import com.Etrial.domain.*;
import com.Etrial.service.CarritoDetalleService;
import com.Etrial.service.CarritoService;
import com.Etrial.service.EventoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    EventoService eventoService;

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    CarritoService carritoService;

    @Autowired
    CarritoDetalleService carritoDetalleService;

    @GetMapping("/")
    public String inicio(Model model, HttpServletRequest request) {

        //Obtener el usuario logeado
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = null;

        if (principal instanceof UserDetails) {
            user = (UserDetails) principal;
        }

        boolean esCliente = false;

        //Validar si es un usuario loggeado
        if (user != null) {
            //Consultar los detalles del usuario
            Usuario usuario = usuarioDao.findByUsername(user.getUsername());

            //Validar si el usuario es de un cliente
            if (usuario.getIdCliente() != null && usuario.getIdCliente() != 0) {
                esCliente = true;
                //Consultamos el carrito del cliente
                Carrito carrito = carritoService.getCarritoCliente(usuario.getIdCliente());
                //Guardamos en sesión los valores importantes
                request.getSession().setAttribute("idCliente", usuario.getIdCliente());
                request.getSession().setAttribute("idCarrito", carrito.getIdCarrito());
                request.getSession().setAttribute("esCliente", esCliente);

                //Consultar items del carrito
                List<CarritoDetalle> carritoDetalles = carritoDetalleService.getCarritoDetalles(carrito.getIdCarrito());
                model.addAttribute("cantidadArticulosCarrito", carritoDetalles.size());
            }
        }

        var eventos = eventoService.getEventos(false);
        model.addAttribute("listaEventos", eventos);
        model.addAttribute("esCliente", esCliente);
        return "index";
    }

}
