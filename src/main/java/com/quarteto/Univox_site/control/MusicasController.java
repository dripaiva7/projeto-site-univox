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

     @PostMapping("/cadastrar")
    public Musicas cadastrar(@RequestBody Musicas musicas){
        return repository.save(musicas);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<Musicas> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(musicas -> ResponseEntity.ok(musicas))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public Musicas atualizar(@RequestBody Musicas musicas) {
        return repository.save(musicas);
    }


}
