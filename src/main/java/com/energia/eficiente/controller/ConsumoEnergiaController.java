package com.energia.eficiente.controller;

import com.energia.eficiente.model.Alerta;
import com.energia.eficiente.model.ConsumoEnergia;
import com.energia.eficiente.service.ConsumoEnergiaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ConsumoEnergia buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }


    @PostMapping
    public ConsumoEnergia salvar(@RequestBody ConsumoEnergia c) {
        return service.salvar(c);
    }

    @PutMapping("/{id}")
    public ConsumoEnergia atualizar(@PathVariable Long id, @RequestBody ConsumoEnergia consumoEnergia){
        consumoEnergia.setId(id);
        return service.atualizar(consumoEnergia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

