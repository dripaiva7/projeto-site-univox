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
    @PostMapping("/cadastrar")
    public Partituras cadastrar(@RequestBody Partituras partituras){
        return repository.save(partituras);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<Partituras> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(partituras -> ResponseEntity.ok(partituras))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public Partituras atualizar(@RequestBody Partituras partituras) {
        return repository.save(partituras);
    }


}
