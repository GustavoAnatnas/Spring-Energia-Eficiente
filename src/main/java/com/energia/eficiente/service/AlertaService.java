package com.energia.eficiente.service;

import com.energia.eficiente.model.Alerta;
import com.energia.eficiente.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository repo;

    public List<Alerta> listar() {
        return repo.findAll();
    }

    public Alerta salvar(Alerta alerta) {
        return repo.save(alerta);
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Alerta atualizar(Long id, Alerta novoAlerta) {
        return repo.findById(id).map(alerta -> {
            alerta.setTipo(novoAlerta.getTipo());
            alerta.setDescricao(novoAlerta.getDescricao());
            alerta.setData(novoAlerta.getData());
            return repo.save(alerta);
        }).orElseThrow(() -> new RuntimeException("Alerta não encontrado com id " + id));
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Alerta não encontrado com id " + id);
        }
        repo.deleteById(id);
    }
}
