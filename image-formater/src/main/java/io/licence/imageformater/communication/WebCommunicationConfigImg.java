package io.licence.imageformater.communication;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class WebCommunicationConfigImg {

    private final ApiConfigurationImg apiConfig;

    @Bean
    @LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


    @Bean
    public ApiClientImg apiClient() {
        ApiClientImg apiClientImg = new ApiClientImg(restTemplate());
        apiClientImg.setBasePath(apiConfig.getBasePath());

        return apiClientImg;
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