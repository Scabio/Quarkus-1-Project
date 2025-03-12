package it.lutech;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
class PersonControllerIT extends PersonControllerTest {
    // Execute the same tests but in packaged mode.

    protected PersonControllerIT(PersonControllerProperties testCasesProperties) {
        super(testCasesProperties);
    }
}
