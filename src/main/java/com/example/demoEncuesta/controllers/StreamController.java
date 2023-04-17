package com.example.demoEncuesta.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demoEncuesta.entities.StreamEntity;
import com.example.demoEncuesta.models.responses.StreamRest;
import com.example.demoEncuesta.services.StreamService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streams")
public class StreamController {
    
    @Autowired
    StreamService streamService;

    @GetMapping
    public List<StreamRest> getStreams(){

        List<StreamEntity> streamsEntities = streamService.getStreams();
        
        List<StreamRest> streamsRest = new ArrayList<>();

        for (int i = 0; i < streamsEntities.size(); i++  ){
            StreamRest stream = new StreamRest();

            stream.setIdStream(streamsEntities.get(i).getId());
            stream.setDescriptionStream(streamsEntities.get(i).getDescription());

            streamsRest.add(stream);
            System.out.println("");
        }
        
        return streamsRest;
    }

    @GetMapping(path = "{id}")
    public StreamRest getPollWithQuestions(@PathVariable long id) {

        StreamEntity stream = streamService.getStreamById(id);
        
        //Vamos a mapear la encuesta al Rest
        ModelMapper mapper = new ModelMapper();
        
        return mapper.map(stream, StreamRest.class);
    }
}
