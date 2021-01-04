package io.licence.webapp.features.authentification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
public class TokenProperties {

  @Value("${app.auth.tokenSecret}")
  private String tokenSecret;

  @Value("${app.auth.tokenExpirationMsec}")
  private int tokenExpirationMsec;

}
