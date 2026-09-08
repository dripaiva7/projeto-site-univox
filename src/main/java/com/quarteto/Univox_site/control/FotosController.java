package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quarteto.univox_site.model.Fotos;
import com.quarteto.univox_site.repository.FotosRepository;

@RestController
@RequestMapping("/fotos")
public class FotosController {
    final FotosRepository repository;

    public FotosController(FotosRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    public List<Fotos> todos(){
        return repository.findAll();

    }

}
