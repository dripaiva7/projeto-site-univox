package com.quarteto.univox_site.control;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
       
    @PostMapping("/cadastrar")
    public Agenda cadastrar(@RequestBody Agenda agenda){
        return repository.save(agenda);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(agenda -> ResponseEntity.ok(agenda))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public Agenda atualizar(@RequestBody Agenda agenda) {
        return repository.save(agenda);
    }
    
}
