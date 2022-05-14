package br.com.aco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Dev")
public class ConfigAmbienteDev {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Executando Ambiente de Dev Oliveira");
        };
    }
}
