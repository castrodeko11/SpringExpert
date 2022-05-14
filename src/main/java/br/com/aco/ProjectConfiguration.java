package br.com.aco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {

    @Bean(name = "applcationName")
    public String applcationName(){
        return "OS ACO";
    }


    @Bean(name = "applcationNameConfig")
    public String applcationNameConfig(){
        return "OS ACO Config";
    }

}
