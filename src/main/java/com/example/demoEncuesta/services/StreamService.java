package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.StreamEntity;

public interface StreamService {
    
    public List<StreamEntity> getStreams();

    public StreamEntity getStreamById(long id);
}
