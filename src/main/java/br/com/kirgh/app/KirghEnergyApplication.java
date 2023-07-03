package br.com.kirgh.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for running KIRGH Energy REST application in Java.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "KIRGH Energy",
                version = "0.0.1",
                description = "Solution for tracking appliance and home electronic energy use through web APIs",
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/bigois/kirgh-energy/blob/main/LICENSE"
                )
        ),
        servers = {@Server(
                url = "https://kirgh-energy.up.railway.app")}
)
public class KirghEnergyApplication {
    /**
     * This is the main function that runs KIRGH Energy REST application in Java.
     *
     * @param args Array of type java.lang.String class that stores java command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(KirghEnergyApplication.class, args);
    }
}
