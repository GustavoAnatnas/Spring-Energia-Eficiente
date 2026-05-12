package com.energia.eficiente.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class ConsumoSteps {

    private Response response;
    private String token;
    private String emailUsuario;
    private String senhaUsuario;
    private final String BASE_URL = "http://localhost:8080";
    private Map<String, Object> corpoRequisicao;

    @Dado("que eu registro um novo usuario com email {string} e senha {string}")
    public void registrarUsuario(String email, String senha) {
        this.emailUsuario = email;
        this.senhaUsuario = senha;

        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("password", senha);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userMap)
                .post(BASE_URL + "/api/auth/register");

        if (response.statusCode() != 201 && response.statusCode() != 400) {
            throw new AssertionError("Falha no registro: " + response.asString());
        }
    }

    @Dado("que eu estou autenticado no sistema")
    public void autenticar() {
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("email", emailUsuario);
        loginMap.put("password", senhaUsuario);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginMap)
                .post(BASE_URL + "/api/auth/login");

        response.then().statusCode(200);
        token = response.jsonPath().getString("token");
    }

    @Quando("eu envio uma requisicao GET para {string}")
    public void enviarGet(String url) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get(url);
    }

    @Dado("que eu tenho os dados de consumo validos")
    public void dadosValidos() {
        corpoRequisicao = new HashMap<>();
        corpoRequisicao.put("empresa", "Empresa ESG");
        corpoRequisicao.put("consumoKw", 250.0);
    }

    @Quando("eu envio uma requisicao GET para {string} sem token")
    public void enviarGetSemToken(String url) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(url);
    }

    @Quando("eu envio uma requisicao POST para {string}")
    public void enviarPost(String url) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(corpoRequisicao)
                .post(url);
    }

    @Então("o status code deve ser {int}")
    public void validarStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Então("o corpo da resposta deve conter uma lista de consumos")
    public void validarLista() {
        response.then().body("$", instanceOf(java.util.List.class));
    }
}