package org.scoula.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component //핵심요소(객체지향에서 핵심요소 객체임)
//프로그래머가 만든 클래스위에 어노테이션을 설정해놓으면
//스프링이 싱글톤으로 만들어서 관리함.
public class Parrot {
    private String name; //필드

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}