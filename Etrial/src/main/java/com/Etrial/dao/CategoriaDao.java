package com.Etrial.dao;

import com.Etrial.domain.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDao extends CrudRepository<Categoria, Long>  {
    
}
