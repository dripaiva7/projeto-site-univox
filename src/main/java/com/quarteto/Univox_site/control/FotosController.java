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

     @PostMapping("/cadastrar")
    public Fotos cadastrar(@RequestBody Fotos fotos){
        return repository.save(fotos);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<Fotos> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(fotos -> ResponseEntity.ok(fotos))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public Fotos atualizar(@RequestBody Fotos fotos) {
        return repository.save(fotos);
    }

}
