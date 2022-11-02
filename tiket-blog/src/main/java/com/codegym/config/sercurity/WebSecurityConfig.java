package com.codegym.config.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public JwtRequestFilter jwtRequestFilter(){
        return new JwtRequestFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/report-employee","/api/report-airline","/api/report-price","/api/employee/**","/api/news/create",
                        "/api/news/update")
                .hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/flight/list","/api/ticket/list","/api/ticket/listHistory",
                        "/api/ticket/getPrice","/api/ticket/page","/api/ticket/delete","/api/ticket/search",
                        "/api/ticket/findTicket","/api/ticket/editTicket","/api/customer/list","/api/flight/create","/api/flight/update"
                )
                .hasAnyAuthority("ROLE_EMPLOYEE")
                .antMatchers("**").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe().userDetailsService(userDetailsService)
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    }
