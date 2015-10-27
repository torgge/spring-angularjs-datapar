package com.itegraldominio.teste;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.integraldominio.DemoTeste1Application;
import com.integraldominio.modelo.Pessoa;
import com.integraldominio.repository.PessoaRepository;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = DemoTeste1Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class PessoaControllerTest {

    @Autowired   // 5
    PessoaRepository repository;

    Pessoa mickey;
    Pessoa minnie;
    Pessoa pluto;

    @Value("${local.server.port}")   // 6
    int port;

    @Before
    public void setUp() {
        // 7
        mickey = new Pessoa("Mickey Mouse");
        minnie = new Pessoa("Minnie Mouse");
        pluto = new Pessoa("Pluto");

        // 8
        repository.deleteAll();
        repository.save(Arrays.asList(mickey, minnie, pluto));

        // 9
        RestAssured.port = port;
    }

    // 10
    @Test
    public void canFetchMickey() {
        Integer mickeyId = mickey.getId();

        when().
                get("/pessoa/{id}", mickeyId).
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.is("Mickey Mouse")).
                body("id", Matchers.is(mickeyId));
    }

    @Test
    public void canFetchAll() {
        when().
                get("/pessoa").
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
    }

    @Test
    public void canDeletePluto() {
        Integer plutoId = pluto.getId();

        when()
                .delete("/pessoa/{id}", plutoId).
        then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }
}