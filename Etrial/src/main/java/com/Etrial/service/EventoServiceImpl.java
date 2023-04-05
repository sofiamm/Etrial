package com.Etrial.service;

import com.Etrial.dao.EventoDao;
import com.Etrial.domain.Evento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoDao eventoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventos(boolean activos) {
        var lista = (List<Evento>) eventoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Evento getEvento(Evento evento) {
        return eventoDao.findById(evento.getIdEvento()).orElse(null);
    }

    @Override
    @Transactional
    public void saveEvento(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    @Transactional
    public void deleteEvento(Evento evento) {
        eventoDao.deleteById(evento.getIdEvento());
    }

}
