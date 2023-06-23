package br.com.kirgh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for running KIRGH Energy REST application in Java.
 */
@SpringBootApplication
public class KirghEnergyApplication {
    /**
     * This is the main function that runs KIRGH Energy REST application in Java.
     * @param args Array of type java.lang.String class that stores java command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(KirghEnergyApplication.class, args);
    }
}
