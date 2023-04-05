package com.Etrial.dao;

import com.Etrial.domain.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoDao extends CrudRepository<Evento, Long>  {
    
}
