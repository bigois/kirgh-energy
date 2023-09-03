package br.com.kirgh.app.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The {@code CorsConfiguration} class is a Java configuration class that allows cross-origin resource sharing (CORS) for all
 * origins and only allows all CRUD basic HTTP methods.
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    /**
     * The {@code addCorsMappings} function allows cross-origin resource sharing (CORS) for all origins and only allows all
     * CRUD basic methods.
     *
     * @param registry The registry parameter is an instance of the {@code CorsRegistry} class. It is used to configure
     *                 Cross-Origin Resource Sharing (CORS) for the application.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
