package com.Etrial.service;

import com.Etrial.dao.ClienteDao;
import com.Etrial.dao.CreditoDao;
import com.Etrial.domain.Cliente;
import com.Etrial.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    CreditoDao creditoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    @Transactional
    public void saveCliente(Cliente cliente) {
        Credito credito = cliente.getCredito();
        credito = creditoDao.save(credito);

        cliente.setCredito(credito);
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void deleteCliente(Cliente cliente) {
        clienteDao.deleteById(cliente.getIdCliente());
    }

    @Override
    public List<Cliente> getClientePorNombre(String nombre) {
        return clienteDao.findByNombre(nombre);
    }

    @Override
    public List<Cliente> getClientePorApellidos(String apellidos) {
        return clienteDao.findByApellidos(apellidos);
    }

    @Override
    public List<Cliente> getClientePorTelefono(String telefono) {
        return clienteDao.findByTelefono(telefono);
    }

    @Override
    public List<Cliente> getClientePorNombreApellidoTelefono(String nombre, String apellido, String telefono) {
        return clienteDao.findByNombreOrApellidosOrTelefono(nombre, apellido, telefono);
    }

}
