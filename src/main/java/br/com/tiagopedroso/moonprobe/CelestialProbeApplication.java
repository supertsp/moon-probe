package br.com.tiagopedroso.moonprobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CelestialProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelestialProbeApplication.class, args);
		System.out.println("\nNow the Celestial Probe API is running ;)");
	}

}
