package com.codecool.employeemanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    @Autowired
    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/user").permitAll()
                    .antMatchers(HttpMethod.POST,"/sign-in").permitAll()
                    .antMatchers(HttpMethod.POST,"/sign-up").permitAll()
                    .antMatchers(HttpMethod.GET,"/sign-out").authenticated()

                    .antMatchers(HttpMethod.GET,"/").authenticated()
                    .antMatchers(HttpMethod.GET,"/levels").authenticated()
                    .antMatchers(HttpMethod.GET,"/statuses").authenticated()

                    .antMatchers(HttpMethod.GET,"/employees").authenticated()
                    .antMatchers(HttpMethod.GET, "/employees/{id}").authenticated()
                    .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/employees/{id}/partial-update").hasRole("SUPERVISOR")
                    .antMatchers(HttpMethod.DELETE, "/employees/{id}/delete").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET,"/departments").authenticated()
                    .antMatchers(HttpMethod.POST, "/departments").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/departments/{id}/delete").hasRole("ADMIN")

                    .antMatchers(HttpMethod.GET,"/positions").authenticated()
                    .antMatchers(HttpMethod.POST, "/positions").hasRole("SUPERVISOR")
                    .antMatchers(HttpMethod.DELETE, "/positions/{id}/delete").hasRole("SUPERVISOR")
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }
}
