package com.example.APIREST2.services;

import com.example.APIREST2.entities.Autor;
import com.example.APIREST2.entities.Localidad;
import com.example.APIREST2.repositories.BaseRepository;
import com.example.APIREST2.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService{

    @Autowired
    private LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Localidad> search(String filtro) throws Exception {
        try {
            List<Localidad> localidades = localidadRepository.search(filtro);
            return localidades;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Localidad> search(String filtro, Pageable pageable) throws Exception {
        try {
            Page<Localidad> localidades = localidadRepository.search(filtro, pageable);
            return localidades;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
