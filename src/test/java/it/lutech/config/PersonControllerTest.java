package it.lutech.config;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import it.lutech.PersonControllerProperties;
import it.lutech.common.test.TestCasesEngine;

@QuarkusTest
@TestProfile(PersonControllerProfile.class)
public class PersonControllerTest extends TestCasesEngine {

    // Execute all tests in application-test.properties
    public PersonControllerTest(PersonControllerProperties testCasesProperties) {
        super(testCasesProperties);
    }
}