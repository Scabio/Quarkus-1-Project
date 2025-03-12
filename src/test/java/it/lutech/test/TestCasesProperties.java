package it.lutech.test;

import java.util.List;
import java.util.Optional;

public interface TestCasesProperties {
    Optional<TestCase> defaultValues();
    List<TestCase> cases();

    interface TestCase {
        String name();
        Optional<String> type();
        Optional<String> url();
        Optional<String> query();
        Optional<String> body();
        Optional<String> headers();
        Optional<String> contentType();
        Optional<Integer> expectedStatus();
        Optional<String> expectedContentType();
        Optional<String> expectedBody();
        Optional<String> expectedHeaders();
    }
}
