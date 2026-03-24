package project.intern.ticket_booking_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //Tắt bảo vệ CSRF (vì dùng JWT)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/auth/**").permitAll() // 2. Cho phép Register/Login không cần Token
                        .anyRequest().authenticated()// 3. Các API khác bắt buộc phải có Token
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//4. KHÔNG tạo Session trên Server
        
        ;
        return http.build();
    }
}
