package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quarteto.univox_site.model.Albuns;
import com.quarteto.univox_site.repository.AlbunsRepository;

@RestController
@RequestMapping("/album")
public class AlbunsController {
    final AlbunsRepository repository;

    public AlbunsController(AlbunsRepository repository) {
        this.repository = repository;
    }

     @GetMapping("/todos")
    public List<Albuns> todos(){
        return repository.findAll();

    }
    

}
