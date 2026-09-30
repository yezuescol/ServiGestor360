package com.prueba.prueba.service;

import com.prueba.prueba.model.Tecnico;

import java.util.List;
import java.util.Optional;

public interface TecnicoService {

    List<Tecnico> listarTecnicos();

    Optional<Tecnico> buscarPorId(Long idTecnico);

    Tecnico guardarTecnico(Tecnico tecnico);

    Tecnico actualizarTecnico(Long idTecnico, Tecnico tecnico);

    void eliminarTecnico(Long idTecnico);

    Optional<Tecnico> buscarPorCorreo(String correo);
}