package com.energia.eficiente;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Onde estão as histórias
        glue = "br.com.fiap.steps",                // Onde está o cérebro
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Para criar um relatório
        monochrome = true                          // Para as letras no console ficarem limpas
)
public class CucumberTest {
}
