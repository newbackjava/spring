package org.scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person2 {

    //어노테이션으로 주입(조립)하는 방법 3가지
    //1. 필드위에 표시 --> 예전에 많이 씀. 아주 간단!
    //   Person2먼저 만들고 나중에 Parrot을 끼는 방식, 비선호
    //2. 생성자에 표시 --> 권장! 미리만들어서 객체생성할 때 주입!
    //3. set메서드에 표시 --> 비권장! Parrot을 나중에 끼고 싶을 때 사용(가끔 사용)

    private String name;

    //싱글톤중에서 자동으로 Parrot2의 클래스로 만들어진 것 있으면 주입해줘
    //@Autowired
    //private final Parrot2 parrot; //생성자로 주입한 것은 final을 써서
    //처음에 주입한 parrot을 계속 사용할 수 있는 안정성을 확보!
    //불변성을 확보 가능

    private Parrot2 parrot;

    //@Autowired
    Person2(Parrot2 parrot){
        this.parrot = parrot;
    }

    public Parrot2 getParrot() {
        return parrot;
    }

    public String getName() {
        return name;
    }

    @Autowired
    public void setParrot(Parrot2 parrot) {
        this.parrot = parrot;
    }

    public void setName(String name) {
        this.name = name;
    }
}

