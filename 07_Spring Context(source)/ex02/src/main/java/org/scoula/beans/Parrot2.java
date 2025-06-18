package org.scoula.beans;

import org.springframework.stereotype.Component;

@Component
public class Parrot2 {
    //    private String name;
    private String name = "Koko";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot : " + name;
    }

}
