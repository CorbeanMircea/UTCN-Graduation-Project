package com.backend.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/signup").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/users").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/users/{_id}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/deleteUser/{productId}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/addProduct/{productId}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/listProducts/{productId}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/deleteProduct/{_id}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/updateProduct/{_id}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/addDonation").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/getUserInfo/{username}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/listAllProducts").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/donations").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/addMessage").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/messages").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/addEvent").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/events").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/deleteEvent/{eventCode}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/bookVolunteerSpots/{eventCode}").allowedOrigins("http://localhost:63342/");
                registry.addMapping("/getEvent/{name}").allowedOrigins("http://localhost:63342/");
            }
        };
    }

}
