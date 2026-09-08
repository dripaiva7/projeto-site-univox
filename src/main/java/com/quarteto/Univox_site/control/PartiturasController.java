package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quarteto.univox_site.model.Partituras;
import com.quarteto.univox_site.repository.PartiturasRepository;

@RestController
@RequestMapping("/partituras")
public class PartiturasController {
    final PartiturasRepository repository;

    public PartiturasController(PartiturasRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/todos")
    public List<Partituras> todos(){
        return repository.findAll();

    }

}
