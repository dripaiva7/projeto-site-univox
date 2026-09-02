package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quarteto.univox_site.model.Agenda;
import com.quarteto.univox_site.repository.AgendaRepository;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    final AgendaRepository repository;

    public AgendaController(AgendaRepository repository) {
        this.repository = repository;
    }

     @GetMapping("/todos")
    public List<Agenda> todos(){
        return repository.findAll();

    }
    
}
