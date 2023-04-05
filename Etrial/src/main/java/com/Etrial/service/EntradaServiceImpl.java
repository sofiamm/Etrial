package com.Etrial.service;

import com.Etrial.dao.EntradaDao;
import com.Etrial.domain.Entrada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntradaServiceImpl implements EntradaService {

    @Autowired
    EntradaDao entradaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Entrada> getEntradas(boolean activos) {
        var lista = (List<Entrada>) entradaDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo()); //4= 3A y 1I
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Entrada getEntrada(Entrada entrada) {
        return entradaDao.findById(entrada.getIdEntrada()).orElse(null);
    }

    @Override
    @Transactional
    public void saveEntrada(Entrada entrada) {
        entradaDao.save(entrada);
    }

    @Override
    @Transactional
    public void deleteEntrada(Entrada entrada) {
        entradaDao.deleteById(entrada.getIdEntrada());
    }

}
