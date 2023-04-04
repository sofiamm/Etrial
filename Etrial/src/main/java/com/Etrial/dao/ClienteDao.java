package com.Etrial.dao;

import com.Etrial.domain.Cliente;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Long> {

    public List<Cliente> findByNombre(String nombre);

    public List<Cliente> findByApellidos(String apellidos);

    public List<Cliente> findByTelefono(String telefono);

    public List<Cliente> findByNombreOrApellidosOrTelefono(String nombre, String apellidos, String telefono);

}
