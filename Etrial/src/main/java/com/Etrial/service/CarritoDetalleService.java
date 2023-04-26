package com.Etrial.service;

import com.Etrial.domain.CarritoDetalle;
import com.Etrial.domain.Entrada;
import java.util.List;

public interface CarritoDetalleService {

    public CarritoDetalle getCarritoDetalle(Long idCarrito, Entrada entrada);

    public List<CarritoDetalle> getCarritoDetalles(Long idCarrito);

    public void save(CarritoDetalle carritoDetalle);

    public void delete(CarritoDetalle carritoDetalle);

    public void deleteAll(Long idCarrito);

}
