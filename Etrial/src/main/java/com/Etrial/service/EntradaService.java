package com.Etrial.service;

import com.Etrial.domain.Entrada;
import java.util.List;

public interface EntradaService {
    public List<Entrada> getEntradas(boolean activos);

    public Entrada getEntrada(Entrada entrada);

    public void saveEntrada(Entrada entrada);

    public void deleteEntrada(Entrada entrada);
}
