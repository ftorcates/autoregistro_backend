package com.example.demoEncuesta.repositories;

import java.util.List;

import com.example.demoEncuesta.entities.StreamEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends CrudRepository<StreamEntity, Long> {
    
    public StreamEntity findById(long id);

    public List<StreamEntity> findAll();
}
