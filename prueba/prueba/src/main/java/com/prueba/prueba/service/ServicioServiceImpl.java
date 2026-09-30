package com.prueba.prueba.service;

import com.prueba.prueba.model.Servicio;
import com.prueba.prueba.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> buscarPorId(Long idServicio) {
        return servicioRepository.findById(idServicio);
    }

    @Override
    public Servicio guardarServicio(Servicio servicio) {

        if (servicioRepository.existsByNombreServicio(servicio.getNombreServicio())) {
            throw new RuntimeException("El servicio ya se encuentra registrado.");
        }

        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio actualizarServicio(Long idServicio, Servicio servicio) {

        Servicio existente = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        existente.setNombreServicio(servicio.getNombreServicio());
        existente.setDescripcion(servicio.getDescripcion());
        existente.setPrecioBase(servicio.getPrecioBase());
        existente.setDuracionEstimada(servicio.getDuracionEstimada());
        existente.setActivo(servicio.getActivo());

        return servicioRepository.save(existente);
    }

    @Override
    public void eliminarServicio(Long idServicio) {

        if (!servicioRepository.existsById(idServicio)) {
            throw new RuntimeException("Servicio no encontrado");
        }

        servicioRepository.deleteById(idServicio);
    }

    @Override
    public Optional<Servicio> buscarPorNombreServicio(String nombreServicio) {
        return servicioRepository.findByNombreServicio(nombreServicio);
    }
}
