package br.com.tiagopedroso.moonprobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoonProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonProbeApplication.class, args);
		System.out.println("\nNow the Moon Probe API is running ;)");
	}

}
