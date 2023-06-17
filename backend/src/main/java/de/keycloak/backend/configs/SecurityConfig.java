package de.keycloak.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final ErrorHandler errorHandler;

  public SecurityConfig(final ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Bean
  protected SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(r -> r.anyRequest().authenticated())
        .exceptionHandling(e -> e.authenticationEntryPoint(errorHandler))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(
            httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer.jwt(
                    jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtConverter())));

    return http.build();
  }

  private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter() {
    final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
    return jwtAuthenticationConverter;
  }
}
