package com.example.demoEncuesta.repositories;

import java.util.List;

import com.example.demoEncuesta.entities.ErrorSummaryEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorSummaryRepository extends CrudRepository<ErrorSummaryEntity, Long> {
    
    public ErrorSummaryEntity findById(long id);

    public List<ErrorSummaryEntity> findByErrorId(long errorId);
}
