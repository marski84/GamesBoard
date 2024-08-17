package org.localhost;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot () {
        Parrot p = new Parrot();
        p.setName("koko");
        return p;
    }

}
