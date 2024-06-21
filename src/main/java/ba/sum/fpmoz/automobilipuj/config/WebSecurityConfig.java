package ba.sum.fpmoz.automobilipuj.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/korisnici/register", "/login_page", "/register").permitAll()  // Omoćuju se ove stranice
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login_page")  // GET zahtjev koji služi stranicu za prijavu
                        .loginProcessingUrl("/login")  // POST cilj iz forme za prijavu
                        .defaultSuccessUrl("/home", true)  // Preusmjeri na '/home' nakon uspješne prijave
                        .failureUrl("/login_page?error=true")  // Ostani na login stranici uz dodatak parametra error
                        .permitAll())

                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login_page")  // Redirect na login stranicu nakon odjave
                .invalidateHttpSession(true)  // Invalidira sesiju
                .clearAuthentication(true)  // Čisti autentifikaciju
                .permitAll());



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }
}
