package org.ncapas.jujustsuapi.controller;

import org.ncapas.jujustsuapi.model.Sorcerer;
import org.ncapas.jujustsuapi.service.SorcererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sorcerers")
public class SorcererController {
    @Autowired
    SorcererService sorcererService;

    //Crear hechicero
    @PostMapping
    public ResponseEntity<Sorcerer> create(@RequestBody Sorcerer sorcerer){
        sorcererService.save(sorcerer);
        return ResponseEntity.status(HttpStatus.CREATED).body(sorcerer);
    }

    //Obtener todos
    @GetMapping
    public ResponseEntity<List<Sorcerer>> findAll(){
        sorcererService.findAll();
        return ResponseEntity.ok(sorcererService.findAll());
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Sorcerer> findById(@PathVariable UUID id){
        sorcererService.findById(id);
        return ResponseEntity.ok(sorcererService.findById(id));
    }

    //Actualizar por id
    @PutMapping("/{id}")
    public ResponseEntity<Sorcerer> update(@PathVariable UUID id, @RequestBody Sorcerer sorcerer){
        sorcererService.update(id, sorcerer);
        return ResponseEntity.ok().build();
    }

    //Eliminar por id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        sorcererService.delete(id);
    }
}