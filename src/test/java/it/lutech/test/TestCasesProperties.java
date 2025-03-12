package it.lutech.test;

import java.util.List;

public interface TestCasesProperties {
    TestCase defaultValues();
    List<TestCase> cases();

    interface TestCase {
        String name();
        String type();
        String url();
        String query();
        String body();
        String headers();
        String contentType();
        Integer expectedStatus();
        String expectedContentType();
        String expectedBody();
        String expectedHeaders();
    }
}
