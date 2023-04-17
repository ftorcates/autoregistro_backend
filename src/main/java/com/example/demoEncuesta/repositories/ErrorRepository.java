package com.example.demoEncuesta.repositories;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends CrudRepository<ErrorEntity, Long> {
    
    public ErrorEntity findById(long id);

    public List<ErrorEntity> findByStreamId(long streamId);
}
