package com.Etrial.service;

import com.Etrial.domain.Evento;
import java.util.List;

public interface EventoService {
    
    public List<Evento> getEventos(boolean activos);

    public Evento getEvento(Evento categoria);

    public void saveEvento(Evento categoria);

    public void deleteEvento(Evento categoria);
}
