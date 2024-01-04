package mk.com.kinmkd.kinmkd.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider authProvider;
    private final CustomAuthenticationFailureHandler authHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( (requests) -> requests
                        .requestMatchers("/location/{id}/my-review", "/location/{id}/my-review/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .permitAll()
                        .failureHandler(authHandler)
                        .defaultSuccessUrl("/home", true)
                )
                .logout((logout) -> logout
                        .logoutUrl("/account/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/home")
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }
}
