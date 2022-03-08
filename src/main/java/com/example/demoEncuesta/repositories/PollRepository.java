package com.example.demoEncuesta.repositories;

import com.example.demoEncuesta.entities.PollEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<PollEntity, Long>{
    
}
