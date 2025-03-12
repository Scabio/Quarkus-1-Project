package it.lutech.test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public abstract class TestCasesEngine {
    protected TestCasesProperties testCasesProperties;

    protected TestCasesEngine(TestCasesProperties testCasesProperties) {
        this.testCasesProperties=testCasesProperties;
    }

    @Test
    public void runConfiguredTests() {
        for (TestCasesProperties.TestCase testCase : testCasesProperties.cases()) {
            System.out.println("************ " + testCase.name()+" ************");

            // Estrazione e parsing degli header
            Map<String, String> headers = toMap(testCase.headers());

            // Costruzione e invocazione della richiesta
            ValidatableResponse response = given()
                    .headers(headers)
                    .contentType(testCase.contentType())
                    .queryParams(toMap(testCase.query()))
                    .body(testCase.body())
                    .when()
                    .request(testCase.type(), testCase.url())
                    .then()
                    .statusCode(testCase.expectedStatus())
                    .contentType(testCase.expectedContentType());

            // Se c'è un expectedBody, confrontiamo anche il body della risposta
            if (!testCase.expectedBody().isEmpty()) {
                response.body(equalTo(testCase.expectedBody()));
            }
        }
    }

    // Metodo per trasformare la query string in una mappa
    private Map<String, String> toMap(String query) {
        return Arrays.stream(query.split("&"))
                .map(p -> p.split("="))
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));
    }
}
