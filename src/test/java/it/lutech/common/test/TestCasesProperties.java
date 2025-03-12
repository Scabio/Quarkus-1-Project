package it.lutech.common.test;

import io.smallrye.config.WithDefault;

import java.util.List;
import java.util.Optional;

public interface TestCasesProperties {
    List<TestCase> cases();

    interface TestCase {
        String name();
        @WithDefault("GET")
        String type();
        String url();
        Optional<String> query();
        Optional<String> body();
        Optional<String> headers();
        Optional<String> contentType();
        Optional<Integer> expectedStatus();
        Optional<String> expectedContentType();
        Optional<String> expectedBody();
        Optional<String> expectedHeaders();
        Optional<List<Mock>> mocks();

        interface Mock {
            String type();
            String jsonConfig();
        }
    }
}
