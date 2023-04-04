package com.Etrial.service;

import com.Etrial.dao.ArticuloDao;
import com.Etrial.domain.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    ArticuloDao articuloDao;

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean activos) {
        var lista = (List<Articulo>) articuloDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo()); //4= 3A y 1I
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }

    @Override
    @Transactional
    public void saveArticulo(Articulo articulo) {
        articuloDao.save(articulo);
    }

    @Override
    @Transactional
    public void deleteArticulo(Articulo articulo) {
        articuloDao.deleteById(articulo.getIdArticulo());
    }

}
