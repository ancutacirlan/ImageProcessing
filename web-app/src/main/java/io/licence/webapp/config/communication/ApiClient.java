package io.licence.webapp.config.communication;

import io.licence.webapp.utils.unsplash.Results;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@RequiredArgsConstructor
@Slf4j
public class ApiClient {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final RestTemplate restTemplate;
    private String basePath;

    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public String invokeApi(String path, String credentials) {
        var variable = ComponentBuilder(path, credentials);
        RequestEntity<Object> requestEntity = variable.body(null);

        return restTemplate
                .exchange(requestEntity, String.class).getBody();
    }

    public String[] invokeApiArray(String path, String credentials) {
        var variable = ComponentBuilder(path, credentials);
        RequestEntity<Object> requestEntity = variable.body(null);

        return restTemplate
                .exchange(requestEntity, String[].class).getBody();
    }

    public Results invokeApiResults(String path, String credentials) {
        var variable = ComponentBuilder(path, credentials);
        RequestEntity<Object> requestEntity = variable.body(null);

        return restTemplate
                .exchange(requestEntity, Results.class).getBody();
    }

    private RequestEntity.BodyBuilder ComponentBuilder(String path, String credentials) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(basePath).path(path);

        RequestEntity.BodyBuilder requestBuilder =
                RequestEntity.method(HttpMethod.GET, builder.build().toUri());

        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.header(AUTHORIZATION_HEADER, credentials);

        return requestBuilder;

    }
}