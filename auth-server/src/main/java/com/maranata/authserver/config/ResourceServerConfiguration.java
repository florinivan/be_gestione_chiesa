package com.maranata.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration

public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  /*  public void configure(HttpSecurity http) throws Exception {
        http.anonymous().and()
                .authorizeRequests()
                .antMatchers("/v1").permitAll()
                .antMatchers("/v1/persone").authenticated()
                .antMatchers("/", "/lib/*", "/images/*", "/css/*", "/swagger-ui.js","/swagger-ui.min.js", "/api-docs", "/fonts/*", "/api-docs/*", "/api-docs/default/*", "/o2c.html","index.html","/webjars/**","/hystrix/**").permitAll()
                .anyRequest().authenticated();
    }*/
}