package com.Etrial.service;

import com.Etrial.domain.Articulo;
import java.util.List;

public interface ArticuloService {
    public List<Articulo> getArticulos(boolean activos);

    public Articulo getArticulo(Articulo articulo);

    public void saveArticulo(Articulo articulo);

    public void deleteArticulo(Articulo articulo);
}
