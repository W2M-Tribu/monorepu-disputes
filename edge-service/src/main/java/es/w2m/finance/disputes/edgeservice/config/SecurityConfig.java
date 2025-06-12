package es.w2m.finance.disputes.edgeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("⚠️ Security is DISABLED for all routes in edge-service (mock setup)");

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
