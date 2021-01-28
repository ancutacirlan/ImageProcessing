package io.licence.imagesearchwrapper.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApiConfiguration {

    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;
}