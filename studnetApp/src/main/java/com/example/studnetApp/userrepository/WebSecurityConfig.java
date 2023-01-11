package com.example.studnetApp.userrepository;

import com.example.studnetApp.jwtSecurity.JwtAuthenticationEntryPoint;
import com.example.studnetApp.jwtSecurity.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private  UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return  new UserDetailsServiceImpl();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        return authenticationProvider;
//    }

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
//        super.configure(auth);
//        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected  void configure(HttpSecurity httpSecurity) throws Exception{
//        super.configure(httpSecurity);
            httpSecurity.
                    csrf().disable()
                    .authorizeRequests()
//                    .anyRequest().permitAll()
                    .antMatchers("/api/students").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET).permitAll()
                    .antMatchers("/jwt/login").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            httpSecurity.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
