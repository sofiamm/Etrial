package com.Etrial.controller;

import com.Etrial.domain.CarritoDetalle;
import com.Etrial.domain.Entrada;
import com.Etrial.service.CarritoDetalleService;
import com.Etrial.service.EntradaService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class CarritoController {
    
@Autowired
    CarritoDetalleService carritoDetalleService;

    @Autowired
    EntradaService eventoService;

    @GetMapping("/carrito/agregar/{idEntrada}")
    public String agregar(Entrada evento, HttpSession session) {
        Long idCarrito = (Long) session.getAttribute("idCarrito");
        evento = eventoService.getEntrada(evento);

        //Verrificar si existe en el carrito del cliente
        CarritoDetalle carritoDetalle = carritoDetalleService.getCarritoDetalle(idCarrito, evento);
        if (carritoDetalle != null) {
            carritoDetalle.setCantidad(carritoDetalle.getCantidad() + 1);
        } else {
            carritoDetalle = new CarritoDetalle(idCarrito, evento, evento.getPrecio(), 1);
        }

        carritoDetalleService.save(carritoDetalle);
        return "redirect:/";
    }

    @GetMapping("/carrito/listado")
    public String listado(Model model, HttpSession session) {
        Long idCarrito = (Long) session.getAttribute("idCarrito");
        boolean esCliente = (boolean) session.getAttribute("esCliente");

        List<CarritoDetalle> carritoDetalles = carritoDetalleService.getCarritoDetalles(idCarrito);

        double montoTotal = 0.0;
        double montoImpuestos = 0.0;

        for (CarritoDetalle c : carritoDetalles) {
            montoTotal += c.getPrecio() * c.getCantidad();
        }

        montoImpuestos = montoTotal * 0.15;

        model.addAttribute("carritoDetalles", carritoDetalles);
        model.addAttribute("cantidadEntradasCarrito", carritoDetalles.size());
        model.addAttribute("esCliente", esCliente);
        model.addAttribute("montoImpuestos", montoImpuestos);
        model.addAttribute("montoTotal", montoTotal);

        return "/carrito/listado";
    }

    @GetMapping("/carrito/eliminar/{idDetalle}")
    public String eliminar(CarritoDetalle carritoDetalle) {
        carritoDetalleService.delete(carritoDetalle);

        return "redirect:/carrito/listado";
    }

}