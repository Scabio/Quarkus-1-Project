package it.lutech;

import io.smallrye.config.ConfigMapping;
import it.lutech.test.TestCasesProperties;

@ConfigMapping(prefix = "person-tests")
public interface PersonControllerProperties extends TestCasesProperties {
}
