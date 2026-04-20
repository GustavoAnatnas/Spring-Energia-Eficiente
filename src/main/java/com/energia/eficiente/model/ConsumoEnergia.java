package com.energia.eficiente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ConsumoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empresa;
    private Double consumoKw;
    private LocalDateTime dataRegistro;
    private Boolean alertGerado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Double getConsumoKw() {
        return consumoKw;
    }

    public void setConsumoKw(Double consumoKw) {
        this.consumoKw = consumoKw;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Boolean getAlertGerado() {
        return alertGerado;
    }

    public void setAlertGerado(Boolean alertGerado) {
        this.alertGerado = alertGerado;
    }
}
