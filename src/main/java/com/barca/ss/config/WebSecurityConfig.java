package com.barca.ss.config;

import com.barca.ss.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/home").access("hasRole('USER') or hasRole('ADMINISTRATOR')")
                .antMatchers("/university-control").access("hasRole('ADMINISTRATOR')")
                .antMatchers("/specialityControl").access("hasRole('ADMINISTRATOR')")
                .antMatchers("/examsResult").access("hasRole('USER')")
                .antMatchers("/submissionApply").access("hasRole('USER')")
                .antMatchers("/getSpecialitiesForSubmission").access("hasRole('USER')")
                .antMatchers("/ownSubmission").access("hasRole('USER')")
                .antMatchers("/submissionOnSpeciality").access("hasRole('USER')")
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").usernameParameter("email").passwordParameter("password").and()
                .logout().logoutSuccessUrl("/login?logout").and()
                .exceptionHandling().accessDeniedPage("/403").and()
                .csrf();
    }

    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
