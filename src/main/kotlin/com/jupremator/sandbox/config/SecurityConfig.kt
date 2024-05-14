package com.jupremator.sandbox.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfig {


    @Bean
    fun securityFilterChain(
            http: HttpSecurity,
    ): SecurityFilterChain {

        http
                .cors(Customizer.withDefaults())
                .csrf { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
                .authorizeHttpRequests { request ->
                    request
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/api/**").permitAll()
                            .anyRequest().permitAll()
                }
                .sessionManagement { manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }


}