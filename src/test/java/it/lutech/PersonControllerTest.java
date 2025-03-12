package it.lutech;

import io.quarkus.test.junit.QuarkusTest;
import it.lutech.test.TestCasesEngine;
import it.lutech.test.TestCasesProperties;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PersonControllerTest extends TestCasesEngine {
    // Execute all tests in application-test.properties
    protected PersonControllerTest(PersonControllerProperties testCasesProperties) {
        super(testCasesProperties);
    }
}