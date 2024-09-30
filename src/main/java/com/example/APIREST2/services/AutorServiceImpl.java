package com.example.APIREST2.services;

import com.example.APIREST2.entities.Autor;
import com.example.APIREST2.entities.Persona;
import com.example.APIREST2.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService {

    @Autowired
    private final AutorRepository autorRepository;

    // Constructor que inyecta AutorRepository en la clase base
    public AutorServiceImpl(AutorRepository autorRepository) {
        super(autorRepository); // Llama al constructor de la clase base
        this.autorRepository = autorRepository; // Asigna el repositorio a la variable de instancia
    }

    @Override
    public List<Autor> search(String filtro) throws Exception {
        try {
            List<Autor> autores = autorRepository.search(filtro);
            return autores;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Autor> search(String filtro, Pageable pageable) throws Exception {
        try {
            Page<Autor> autores = autorRepository.search(filtro, pageable);
            return autores;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}


