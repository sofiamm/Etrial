package com.Etrial.service;

import com.Etrial.domain.Carrito;

public interface CarritoService {

    public Carrito getCarrito(Carrito carrito);

    public Carrito getCarritoCliente(Long idCliente);
}
