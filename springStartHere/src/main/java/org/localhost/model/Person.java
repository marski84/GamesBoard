package org.localhost.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Person {
    private String name;
//    @Autowired
    private Parrot parrot;
//    private final Parrot parrot;
//
//    public Person(Parrot parrot) {
//        this.parrot = parrot;
//    }
}
