package org.scoula.app;

import org.scoula.config.ProjectConfig;
import org.scoula.config.ProjectConfig2;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        //스프링이 객체를 생성한 것을 가지고 와서 사용해보자.
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);
        //설정파일에서 만든 싱글톤은 스프링 프로젝트 전체에서 사용됨.
        //이렇게 스프링 프로젝트 전체에서 사용되는 설정을 하는 파일을 컨텍스트 파일
        //컨텍스트 파일에서 설정한 싱글톤 객체를 가지고 오려면 context객체가 필요함.

        //설정값이 다른 싱글톤으로 여러개 만들어야하는 경우
        //하나의 클래스에 해당하는 싱글톤 객체가 많으면 이름으로 다시 필터링해서 가지고 올 수 있음.
        Parrot p1 = context.getBean("miki", Parrot.class);
        System.out.println(p1.getName());
    }
}
