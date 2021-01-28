package io.licence.webapp.config.communication;

import io.licence.webapp.config.communication.ApiConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class WebCommunicationConfig {

    private final ApiConfiguration apiConfig;

    @Bean
    public ApiClient restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(apiConfig.getBasePath());

        return apiClient;
    }

    public String getAuthorization() {
        return (!StringUtils.isEmpty(apiConfig.getUsername()) &&
                !StringUtils.isEmpty(apiConfig.getPassword())) ?
                "Basic " + Base64Utils.encodeToString((
                        apiConfig.getUsername() + ":" + apiConfig.getPassword())
                        .getBytes()) :
                null;
    }
}