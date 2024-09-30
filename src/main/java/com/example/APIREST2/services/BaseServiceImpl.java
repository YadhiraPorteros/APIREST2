package com.example.APIREST2.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.example.APIREST2.repositories.BaseRepository;
import com.example.APIREST2.entities.Base;


public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E,ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entityUpdate) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                E entityFromDB = entityOptional.get();

                // Iteramos sobre los campos de la entidad
                for (Field field : entityUpdate.getClass().getDeclaredFields()) {
                    field.setAccessible(true);

                    Object valueUpdate = field.get(entityUpdate);
                    Object valueFromDB = field.get(entityFromDB);

                    // Si el campo es una colección, lo actualizamos en lugar de reemplazar
                    if (valueFromDB instanceof Collection && valueUpdate instanceof Collection) {
                        Collection<?> collectionFromDB = (Collection<?>) valueFromDB;
                        Collection<?> collectionUpdate = (Collection<?>) valueUpdate;

                        // Hacemos un casting seguro y validamos que los tipos sean compatibles
                        if (!collectionFromDB.isEmpty() && !collectionUpdate.isEmpty()) {
                            Object itemFromDB = collectionFromDB.iterator().next();
                            Object itemFromUpdate = collectionUpdate.iterator().next();

                            // Comprobamos si los tipos son iguales
                            if (itemFromDB.getClass().equals(itemFromUpdate.getClass())) {
                                collectionFromDB.clear(); // Limpiamos la colección actual
                                collectionFromDB.addAll((Collection) collectionUpdate); // Agregamos los elementos nuevos
                            } else {
                                throw new IllegalArgumentException("Incompatible types between collections");
                            }
                        } else {
                            collectionFromDB.clear(); // Si están vacías, simplemente limpiamos
                            collectionFromDB.addAll((Collection) collectionUpdate); // Agregamos los elementos nuevos
                        }
                    } else {
                        // Para otros campos, simplemente copiamos el valor si no es nulo
                        if (valueUpdate != null) {
                            field.set(entityFromDB, valueUpdate);
                        }
                    }
                }

                entityFromDB = baseRepository.save(entityFromDB); // Guardamos la entidad actualizada
                return entityFromDB;
            } else {
                throw new Exception("Entity not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("Entity not found for deletion");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



}


