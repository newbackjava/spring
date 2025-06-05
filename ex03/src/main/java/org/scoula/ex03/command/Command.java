package org.scoula.ex03.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    public abstract String execute(HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException;

    //인터페이스를 부품(객체로 생성)으로 사용 불가능
    //인터페이스를 클래스로 구현한 다음 객체로 생성할 수 있음.
    //create요청들어오면 execute()안에 해당 해쉬테이블에서 찾은
    //컨트롤러의 메서드를 호출하도록 추상메서드를 구현한 클래스를 만들어 사용함.
//    new Command({
//        public String execute(req, resp){
//            return memberController.create(); //viewname을 리턴!
//        }
//    }) //인터페이스를 구현한 익명클래스가 됨.

}
