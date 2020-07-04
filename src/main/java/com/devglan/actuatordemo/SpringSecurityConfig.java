package com.devglan.actuatordemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http
                .authorizeRequests()
                .antMatchers("/myapp/**").hasRole("ACTADMIN");
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User
                        .withUsername("user")
                        //password is password - https://www.devglan.com/online-tools/bcrypt-hash-generator
                        .password("{bcrypt}$2a$04$fPsaMC/8QJvinvTKzoxEkuzfJ85BiWP2HyR37go9Vf5tEetlN9Im2")
                        .roles("ACTADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                //password is password(Bcrypted with online free tool) - https://www.devglan.com/online-tools/bcrypt-hash-generator
                .password("{bcrypt}$2a$04$fPsaMC/8QJvinvTKzoxEkuzfJ85BiWP2HyR37go9Vf5tEetlN9Im2")
                .roles("ACTADMIN");
    }
}
