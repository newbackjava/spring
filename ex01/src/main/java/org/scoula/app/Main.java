package org.scoula.app;

import org.scoula.config.ProjectConfig;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main{
    public static void main(String[] args) {
        //스프링을 쓰지 않으면 필요할 때 new키워드써서 개발자가 객체를 생성했음.
        //프로그래머가 객체를 생성하는 경우
        Parrot p = new Parrot();
        p.setName("Test");
        System.out.println(p.getName());
        System.out.println(p); //주소1

        //스프링이 객체를 생성한 것을 가지고 와서 사용해보자.
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p2 = context.getBean(Parrot.class); //Parrot클래스로 만들어진 객체 get!
        System.out.println(p2.getName());
        System.out.println(p2); //주소2
        Parrot p3 = context.getBean(Parrot.class); //Parrot클래스로 만들어진 객체 get!
        System.out.println(p3); //주소3
    }
}
