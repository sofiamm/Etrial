package com.Etrial.service;

import com.Etrial.domain.Cliente;
import java.util.List;

public interface ClienteService {

    public List<Cliente> getClientes();

    public Cliente getCliente(Cliente cliente);

    public void saveCliente(Cliente cliente); //Para insertar o modificar - si viene el idCliente o no

    public void deleteCliente(Cliente cliente);

    public List<Cliente> getClientePorNombre(String nombre);

    public List<Cliente> getClientePorApellidos(String apellidos);

    public List<Cliente> getClientePorTelefono(String telefono);

    public List<Cliente> getClientePorNombreApellidoTelefono(String nombre, String apellido, String telefono);

}
