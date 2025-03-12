package it.lutech.common.test;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public abstract class TestCasesEngine {
    protected TestCasesProperties testCasesProperties;

    /** Need call this constructor
     *
     * @param testCasesProperties
     */
    protected TestCasesEngine(TestCasesProperties testCasesProperties) {
        this.testCasesProperties = testCasesProperties;
    }

    @Test
    public void runConfiguredTests() {
        for (TestCasesProperties.TestCase testCase : testCasesProperties.cases()) {
            beforeTest(testCase);
            prepareMocks(testCase);
            executeTest(testCase);
            afterTest(testCase);
        }
    }

    /** This method allows you to perform a single test, calling it by name.
     *
     * @param testName
     */
    protected void singleTest(String testName) {
        TestCasesProperties.TestCase testCase = testCasesProperties.cases().stream().filter(e -> e.name().equals(testName)).findFirst().orElse(null);
        if (testCase != null) {
            executeTest(testCase);
        } else {
            throw new IllegalArgumentException("Test not found: " + testName);
        }
    }


    /** This method implement some generic mocks.
     * Override this method to add extra mock definitions.
     *
     * @param testCase
     */
    protected void prepareMocks(TestCasesProperties.TestCase testCase) {

    }

    /** Override this method to add extra operation before test
     *
     * @param testCase
     */
    protected void beforeTest(TestCasesProperties.TestCase testCase) {
    }

    /** Override this method to add extra operation after test
     *
     * @param testCase
     */
    protected void afterTest(TestCasesProperties.TestCase testCase) {
    }

    private void executeTest(TestCasesProperties.TestCase testCase) {
        System.out.println("************ " + testCase.name() + " ************");

        RequestSpecification given = given()
                .headers(toMap(testCase.headers().orElse(null)))
                .queryParams(toMap(testCase.query().orElse(null)));
        if (testCase.contentType().isPresent()) {
            given = given.contentType(testCase.contentType().get());
        }
        if (testCase.body().isPresent()) {
            given = given.body(testCase.body().get());
        }

        // Costruzione e invocazione della richiesta
        ValidatableResponse response = given
                .log().all()
                .when()
                .request(testCase.type(), testCase.url())
                .then()
                .log().all();

        if (testCase.expectedStatus().isPresent()) {
            response.statusCode(equalTo(testCase.expectedStatus().get()));
        }
        if (testCase.expectedContentType().isPresent()) {
            response.contentType(equalTo(testCase.expectedContentType().get()));
        }
        if (testCase.expectedBody().isPresent()) {
            response.body(equalTo(testCase.expectedBody().get()));
        }
    }

    // Metodo per trasformare la query string in una mappa
    private Map<String, String> toMap(String query) {
        if (query == null) return new HashMap<>();
        return Arrays.stream(query.split("&"))
                .map(p -> p.split("="))
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));
    }
}
