package config;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("test")
@PropertySource("classpath:/config/test.properties")
public class TestConfig {}
