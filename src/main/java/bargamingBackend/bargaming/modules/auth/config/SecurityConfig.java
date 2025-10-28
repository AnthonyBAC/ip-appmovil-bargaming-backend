package bargamingBackend.bargaming.modules.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())

                .cors(cors -> cors.disable())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()

                        // Endpoints protegidos por rol
                        .requestMatchers("/api/client/**").hasAuthority("CLIENTE")
                        .requestMatchers("/api/seller/**").hasAuthority("VENDEDOR")
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                        // Upload profile: cualquier autenticado
                        .requestMatchers("/api/auth/upload-profile").hasAuthority("CLIENTE")
                        .requestMatchers("/api/auth/upload-profile").hasAuthority("VENDEDOR")
                        .requestMatchers("/api/auth/upload-profile").hasAuthority("ADMIN")

                        // Todo lo demás requiere login
                        .anyRequest().authenticated())

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
