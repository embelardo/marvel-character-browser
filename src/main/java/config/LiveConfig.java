package config;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Profile("live")
@PropertySources({
    @PropertySource(value = "classpath:/config/apikeys.properties", ignoreResourceNotFound = true),
    @PropertySource("classpath:/config/application.properties")})
public class LiveConfig {}
