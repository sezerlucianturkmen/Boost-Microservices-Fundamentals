package com.boost.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserServiceSecurityConfig {
    @Bean
    JwtTokenFilter getJwtTokenFilter() {
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * csrf'i kapat
         */
        http.csrf().disable();
        /**
         * Tüm gelen isteklere izin verme
         * http://localhost:9092/v3/api-docs/**[herşey]
         */
        http.authorizeRequests()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**").permitAll()
                .anyRequest().authenticated();
        /**
         * kullanıcı doğrulama işlemini spring form ile yap.
         */
        //http.formLogin();
        /**
         * Gelen her isteği kendi oluşturduğumuz bir sınıf içerisine yönlendireceğiz ve burada
         * gelen token bilgisini kontrol ederek içinde var olan authid ile kullanıcı bilgilerini
         * çekeceğiz. Bu bilgilerin yetki v.s. gibi alanları ile işlem yapmasını sağlayacağız.
         */
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}