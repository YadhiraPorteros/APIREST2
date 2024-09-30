package com.example.APIREST2.services;


import com.example.APIREST2.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PersonaService extends BaseService<Persona, Long> {
    List<Persona> search(String filtro) throws Exception;
    Page<Persona> search(String filtro, Pageable pageable) throws Exception;

}
