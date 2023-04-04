package com.Etrial.service;

import com.Etrial.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    public List<Categoria> getCategorias(boolean activos);

    public Categoria getCategoria(Categoria categoria);

    public void saveCategoria(Categoria categoria);

    public void deleteCategoria(Categoria categoria);
}
