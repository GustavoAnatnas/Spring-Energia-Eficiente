package com.energia.eficiente.controller;

import com.energia.eficiente.model.ConsumoEnergia;
import com.energia.eficiente.service.ConsumoEnergiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo-energia")
public class ConsumoEnergiaController {

    @Autowired
    private ConsumoEnergiaService service;

    @GetMapping
    public List<ConsumoEnergia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoEnergia> buscarPorId(@PathVariable Long id) {
        ConsumoEnergia consumo = service.buscarPorId(id);
        return ResponseEntity.ok(consumo);
    }

    @PostMapping
    public ResponseEntity<ConsumoEnergia> salvar(@Valid @RequestBody ConsumoEnergia c) {
        return ResponseEntity.ok(service.salvar(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoEnergia> atualizar(@PathVariable Long id, @Valid @RequestBody ConsumoEnergia consumoEnergia){
        consumoEnergia.setId(id);
        return ResponseEntity.ok(service.atualizar(consumoEnergia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}