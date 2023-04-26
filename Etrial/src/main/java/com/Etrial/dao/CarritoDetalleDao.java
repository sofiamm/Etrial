package com.Etrial.dao;

import com.Etrial.domain.CarritoDetalle;
import com.Etrial.domain.Entrada;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CarritoDetalleDao extends CrudRepository<CarritoDetalle, Long> {

    List<CarritoDetalle> findByIdCarrito(Long idCarrito);

    CarritoDetalle findByIdCarritoAndEntrada(Long idCarrito, Entrada entrada);

    void deleteByIdCarrito(Long idCarrito);
}