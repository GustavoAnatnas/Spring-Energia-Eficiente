package com.energia.eficiente.service;

import com.energia.eficiente.model.Alerta;
import com.energia.eficiente.model.ConsumoEnergia;
import com.energia.eficiente.repository.ConsumoEnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumoEnergiaService {
    @Autowired
    private ConsumoEnergiaRepository repo;
    @Autowired
    private AlertaService alertaService;

    public List<ConsumoEnergia> listar() {
        return repo.findAll();
    }

    public ConsumoEnergia buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado com id " + id));
    }


    public ConsumoEnergia salvar(ConsumoEnergia c) {
        c.setDataRegistro(LocalDateTime.now());
        if (c.getAlertGerado() == null) c.setAlertGerado(false);

        if (c.getConsumoKw() != null && c.getConsumoKw() > 1000) {
            c.setAlertGerado(true);

            Alerta alerta = new Alerta();
            alerta.setTipo("ALTO_CONSUMO");
            alerta.setDescricao("Consumo elevado detectado: " + c.getConsumoKw() + " kWh");
            alerta.setData(LocalDateTime.now());
            alertaService.salvar(alerta);
        }

        return repo.save(c);
    }


    public ConsumoEnergia atualizar(ConsumoEnergia consumoEnergia) {
        ConsumoEnergia existente = repo.findById(consumoEnergia.getId())
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado com id " + consumoEnergia.getId()));

        existente.setConsumoKw(consumoEnergia.getConsumoKw());
        existente.setEmpresa(consumoEnergia.getEmpresa());
        existente.setDataRegistro(LocalDateTime.now());

        if (consumoEnergia.getConsumoKw() != null && consumoEnergia.getConsumoKw() > 1000) {
            existente.setAlertGerado(true);

            Alerta alerta = new Alerta();
            alerta.setTipo("ALTO_CONSUMO");
            alerta.setDescricao("Consumo atualizado acima do limite: " + consumoEnergia.getConsumoKw() + " kWh");
            alerta.setData(LocalDateTime.now());
            alertaService.salvar(alerta);
        }

        return repo.save(existente);
    }


    public void delete(Long id){
        ConsumoEnergia consumo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado com id " + id));
        repo.delete(consumo);
    }
}

