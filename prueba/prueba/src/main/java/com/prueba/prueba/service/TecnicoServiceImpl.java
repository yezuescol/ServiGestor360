package com.prueba.prueba.service;

import com.prueba.prueba.model.Tecnico;
import com.prueba.prueba.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.findAll();
    }

    @Override
    public Optional<Tecnico> buscarPorId(Long idTecnico) {
        return tecnicoRepository.findById(idTecnico);
    }

    @Override
    public Tecnico guardarTecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico actualizarTecnico(Long idTecnico, Tecnico tecnico) {

        Tecnico existente = tecnicoRepository.findById(idTecnico)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        existente.setNombres(tecnico.getNombres());
        existente.setApellidos(tecnico.getApellidos());
        existente.setCorreo(tecnico.getCorreo());
        existente.setTelefono(tecnico.getTelefono());
        existente.setEspecialidad(tecnico.getEspecialidad());
        existente.setDepartamento(tecnico.getDepartamento());
        existente.setMunicipio(tecnico.getMunicipio());
        existente.setActivo(tecnico.getActivo());

        return tecnicoRepository.save(existente);
    }

    @Override
    public void eliminarTecnico(Long idTecnico) {

        if (!tecnicoRepository.existsById(idTecnico)) {
            throw new RuntimeException("Técnico no encontrado");
        }

        tecnicoRepository.deleteById(idTecnico);
    }

    @Override
    public Optional<Tecnico> buscarPorCorreo(String correo) {
        return tecnicoRepository.findByCorreo(correo);
    }
}