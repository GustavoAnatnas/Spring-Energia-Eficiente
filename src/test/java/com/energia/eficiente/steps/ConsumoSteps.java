package com.energia.eficiente.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.E;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class ConsumoSteps {
    private Response response;
    private String endpoint = "http://localhost:8080/api/consumo";

    @Dado("que eu tenho os dados de consumo válidos")
    public void dadosValidos() {
        // Objeto simulando o modelo ConsumoEnergia do seu projeto
    }

    @Quando("eu envio uma requisição POST para {string}")
    public void enviarPost(String path) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"valor\": 150.5, \"data\": \"2026-05-06\" }")
                .post(path);
    }

    @Então("o status code deve ser {int}")
    public void validarStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @E("o corpo da resposta deve validar o contrato JSON")
    public void validarContrato() {
        // Requisito 2: Validação de JSON Schema
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/consumo-schema.json"));
    }
}