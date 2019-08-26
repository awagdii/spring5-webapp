package com.wagdi.spring5webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER", "ADMIN")
                .and().withUser("user").password("{noop}user").roles("USER");

//        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "logout", "/index").permitAll().and()
                .authorizeRequests().antMatchers("/books").hasRole("USER").and()
                .authorizeRequests().antMatchers("/**").hasRole("ADMIN").and()
                .formLogin().and().logout();
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //turn off security
//        web.ignoring().antMatchers("/**").and().debug(true);
        web.ignoring().antMatchers("/static/**").
                and().debug(true);
    }
}
