package com.backend.project.configuration;

import com.backend.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final
    UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }


    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login").allowedOrigins("http://localhost:63342/");
            }
        };
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers(
                "/signup",
                "/login",
                "/users",
                "/users/{_id}",
                "/deleteUser/{productId}",
                "/addProduct/{productId}",
                "/listProducts/{productId}",
                "/deleteProduct/{_id}",
                "/updateProduct/{_id}",
                "/addDonation",
                "/getUserInfo/{username}",
                "/listAllProducts",
                "/donations",
                "/addMessage",
                "/messages",
                "/addEvent",
                "/events",
                "/deleteEvent/{eventCode}",
                "/bookVolunteerSpots/{eventCode}",
                "/getEvent/{name}"
                )
                .permitAll().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
