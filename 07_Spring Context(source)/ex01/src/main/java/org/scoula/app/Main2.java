package org.scoula.app;

import org.scoula.config.ProjectConfig2;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);

//        Parrot p = context.getBean(Parrot.class);	// 예외 발생 !!!
        Parrot p = context.getBean("miki", Parrot.class);
        System.out.println(p.getName());


    }

}
