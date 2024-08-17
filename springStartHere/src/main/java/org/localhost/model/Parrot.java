package org.localhost.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Parrot {
    private String name;

    public Parrot() {
        System.out.println("creating parrot");
    }
}
