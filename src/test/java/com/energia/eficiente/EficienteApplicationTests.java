package com.energia.eficiente;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EficienteApplicationTests {

    @Disabled("Desativa para CI/CD sem dependência externa")

	@Test
	void contextLoads() {
	}

}
