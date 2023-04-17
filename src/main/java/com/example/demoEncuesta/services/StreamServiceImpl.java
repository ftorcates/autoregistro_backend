package com.example.demoEncuesta.services;

import java.util.List;

import com.example.demoEncuesta.entities.StreamEntity;
import com.example.demoEncuesta.repositories.StreamRepository;

import org.springframework.stereotype.Service;

@Service
public class StreamServiceImpl implements StreamService {

    StreamRepository streamRepository;

    public StreamServiceImpl(StreamRepository streamRepository){
        this.streamRepository = streamRepository;
    }
    @Override
    public List<StreamEntity> getStreams() {
        
        List<StreamEntity> streams = streamRepository.findAll();
        
        return streams;
    }
    @Override
    public StreamEntity getStreamById(long id) {
        
        StreamEntity stream = streamRepository.findById(id);

        return stream;
    }
    
}
