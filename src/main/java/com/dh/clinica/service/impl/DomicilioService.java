package com.dh.clinica.service.impl;


import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.dto.DomicilioDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.impl.DomicilioRepository;
import com.dh.clinica.service.IDomicilioService;
import com.dh.clinica.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {
    /*@Autowired
    private DomicilioRepository domicilioRepository;*/

    private final DomicilioRepository domicilioRepository; // Está bien que no podamos cambiar a posteriori la implementación del servicio?
    private final SpringConfig springConfig;

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository, SpringConfig springConfig) {
        this.domicilioRepository = domicilioRepository;
        this.springConfig = springConfig;
    }

    /*public Domicilio guardar(Domicilio d) {
        return domicilioRepository.save(d);
    }*/

    @Override
    public DomicilioDto guardar(DomicilioDto domicilioDto) throws BadRequestException {
        if (domicilioDto == null)
            throw new BadRequestException("El domicilio no puede ser null");

        Domicilio domicilio = springConfig.getModelMapper().map(domicilioDto, Domicilio.class);
        domicilioDto = springConfig.getModelMapper().map(domicilioRepository.save(domicilio), DomicilioDto.class);
        return domicilioDto;
    }

    @Override
    public List<DomicilioDto> listar() {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        return Mapper.mapList(springConfig.getModelMapper(), domicilios, DomicilioDto.class);
    }

    @Override
    public DomicilioDto actualizar(DomicilioDto domicilioDto) throws BadRequestException, ResourceNotFoundException {
        DomicilioDto domicilioActualizado = null;
        if (domicilioDto == null) throw new BadRequestException("No se pudo actualizar el domicilio null");
        if (domicilioDto.getId() == null) throw new BadRequestException("El ID del domicilio a actualizar no puede ser null");

        Optional<Domicilio> domicilioEnBd = domicilioRepository.findById(domicilioDto.getId());
        if (domicilioEnBd.isPresent()) {
            if (domicilioDto.getCalle() != null) {
                domicilioEnBd.get().setCalle(domicilioDto.getCalle());
            }
            if (domicilioDto.getNumero() != null) {
                domicilioEnBd.get().setNumero(domicilioDto.getNumero());
            }
            if (domicilioDto.getLocalidad() != null) {
                domicilioEnBd.get().setLocalidad(domicilioDto.getLocalidad());
            }
            if (domicilioDto.getProvincia() != null) {
                domicilioEnBd.get().setProvincia(domicilioDto.getProvincia());
            }
            Domicilio actualizado = domicilioEnBd.get();
            Domicilio guardado = domicilioRepository.save(actualizado);
            domicilioActualizado = springConfig.getModelMapper().map(guardado, DomicilioDto.class);
        }
        return domicilioActualizado;
    }

    @Override
    public DomicilioDto buscar(Integer id) throws ResourceNotFoundException, BadRequestException {
        //return domicilioRepository.findById(id).get();
        //return Optional.of(domicilioRepository.getById(id));
        //return domicilioRepository.findById(id);
        if (id == null || id < 1)
            throw new BadRequestException("El ID del domicilio no puede ser null ni negativo");
        Domicilio d = domicilioRepository.findById(id).orElse(null);
        if (d == null)
            throw new ResourceNotFoundException("No se encontró el domicilio con ID " + id);
        return springConfig.getModelMapper().map(d, DomicilioDto.class);
    }

    @Override
    public void eliminar(Integer id) {
        domicilioRepository.deleteById(id);
    }
}
