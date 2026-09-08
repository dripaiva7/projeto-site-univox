package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quarteto.univox_site.model.Musicas;
import com.quarteto.univox_site.repository.MusicasRepository;

@RestController
@RequestMapping("/musicas")
public class MusicasController {
    final MusicasRepository repository;

    public MusicasController(MusicasRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    public List<Musicas> todos(){
        return repository.findAll();

    }


}
