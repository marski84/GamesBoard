package org.localhost.config;

import org.localhost.model.Parrot;
import org.localhost.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.localhost")
public class ProjectConfig {

    @Bean("parrotOwner")
    @Primary
    Person parrotOwner() {
        Person person = new Person();
        person.setName("Parrot Owner");
        person.setParrot(parrot1());
        return person;
    }

    @Bean
    Person parrotOwnerWithInjectionInParameters(Parrot parrot) {
        Person person = new Person();
        person.setName("Parrot Owner second");
        person.setParrot(parrot);
        return person;
    }

    @Bean("kokobean")
    @Primary
    Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("koko");
        return p;
    }

//    @Bean
//    Parrot parrot2() {
//        Parrot p = new Parrot();
//        p.setName("riko");
//        return p;
//    }
//
//    @Bean
//    Parrot parrot3() {
//        Parrot p = new Parrot();
//        p.setName("sriko");
//        return p;
//    }
//
//    @Bean
//    String hello () {
//        return "Hello World";
//    }
//
//    @Bean
//    Integer numberTen() {
//        return 10;
//    }

}
