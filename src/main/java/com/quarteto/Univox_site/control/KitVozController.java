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

     @PostMapping("/cadastrar")
    public KitVoz cadastrar(@RequestBody KitVoz kitVoz){
        return repository.save(kitVoz);
    } 
    
     @GetMapping("/{id}")
    public ResponseEntity<KitVoz> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(kitVoz -> ResponseEntity.ok(kitVoz))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping ("/atualizar")
    public KitVoz atualizar(@RequestBody KitVoz kitVoz) {
        return repository.save(kitVoz);
    }

}
