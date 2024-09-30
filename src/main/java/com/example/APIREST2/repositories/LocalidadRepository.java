package com.example.APIREST2.repositories;

import com.example.APIREST2.entities.Localidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long> {

    @Query(value = "SELECT l FROM Localidad l WHERE l.denominacion LIKE %:filtro%")
    List<Localidad> search(@Param("filtro") String filtro);

    @Query(value = "SELECT l FROM Localidad l WHERE l.denominacion LIKE %:filtro%")
    Page<Localidad> search(@Param("filtro") String filtro, Pageable pageable);

}
