package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quarteto.univox_site.model.KitVoz;
import com.quarteto.univox_site.repository.KitVozRepository;

@RestController
@RequestMapping("/kitvoz")
public class KitVozController {
    final KitVozRepository repository;

    
    public KitVozController(KitVozRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/todos")
    public List<KitVoz> todos(){
        return repository.findAll();

    }

}
