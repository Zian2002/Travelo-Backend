package com.zian.travelo.security;

import com.zian.travelo.entity.ERole;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(request ->{

            request
                    .requestMatchers("/auth/login",
                                    "/auth/register",
                                    "/image/**",
                                    "/location",
                                    "/tourinfo",
                                    "/tour",
                                    "/booking/useradd").permitAll()
                    .requestMatchers("/customer/**",
                                    "/staff/**").hasAnyAuthority(ERole.ROLE_ADMIM.name(),
                                                                    ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.POST, "/tour/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.PUT, "/tour/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.DELETE, "/tour/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())

                    .requestMatchers(HttpMethod.POST, "/tourinfo/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.PUT, "/tourinfo/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.DELETE, "/tourinfo/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name()).requestMatchers(HttpMethod.POST, "/tour/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())

                    .requestMatchers(HttpMethod.POST, "/location/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.PUT, "/location/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .requestMatchers(HttpMethod.DELETE, "/location/").hasAnyAuthority(ERole.ROLE_ADMIM.name(), ERole.ROLE_STAFF.name())
                    .anyRequest().authenticated();

        }).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.httpBasic(httpBasic ->
                httpBasic.authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("json");
                    response.getWriter().write("{\n\tmessgae : \"" + authException.getMessage() + "\"\n}");
                }));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
