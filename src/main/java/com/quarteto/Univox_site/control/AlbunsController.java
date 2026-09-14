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

import com.quarteto.univox_site.model.Albuns;
import com.quarteto.univox_site.repository.AlbunsRepository;

@RestController
@RequestMapping("/albuns")
public class AlbunsController {
    final AlbunsRepository repository;

    public AlbunsController(AlbunsRepository repository) {
        this.repository = repository;
    }

     @GetMapping("/todos")
    public List<Albuns> todos(){
        return repository.findAll();

    }

     @PostMapping("/cadastrar")
    public Albuns cadastrar(@RequestBody Albuns album){
        return repository.save(album);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<Albuns> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(album -> ResponseEntity.ok(album))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public Albuns atualizar(@RequestBody Albuns album) {
        return repository.save(album);
    }
    
    

}
