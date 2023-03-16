package com.etrial.project.service;

import com.etrial.project.domain.Cliente;
import java.util.List;

public interface ClienteService {

    public List<Cliente> getClientes();

    public Cliente getCliente(Cliente cliente);

    public void saveCliente(Cliente cliente); //Para insertar o modificar - si viene el idCliente o no

    public void deleteCliente(Cliente cliente);
}
