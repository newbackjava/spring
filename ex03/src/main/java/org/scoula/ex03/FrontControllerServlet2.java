package org.scoula.ex03;

import org.scoula.ex03.command2.Command;
import org.scoula.ex03.controller2.HomeController;
import org.scoula.ex03.controller2.TodoController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프론트 컨트롤러 서블릿: 모든 요청을 받아 알맞은 컨트롤러로 전달하는 중앙 관리 서블릿
//@WebServlet("/")
public class FrontControllerServlet2 extends HttpServlet {

    // GET 요청에 대한 URL-Command 매핑
    Map<String, Command> getMap;

    // POST 요청에 대한 URL-Command 매핑
    Map<String, Command> postMap;

    // 뷰(JSP)의 경로 설정
    String prefix = "/WEB-INF/views/";
    String suffix = ".jsp";

    // 개별 컨트롤러 객체 생성
    HomeController homeController = new HomeController();
    TodoController todoController = new TodoController();

    // 서블릿 초기화 시 호출되는 메서드
    public void init() {
        
        //get요청별 어떤 메서드를 호출해야하는지 hashmap에 모아보자.
        //hashmap의 키는 요청주소, 값은 호출해야하는 메서드이름
        getMap = new HashMap<>();
        postMap = new HashMap<>();

        // GET 요청에 대한 경로와 처리 메서드 등록 (메서드 참조 사용)
        getMap.put("/", homeController::getIndex);

        // GET 요청 등록 - 메서드 참조 제거
//        getMap.put("/", new Command() {
//            public String execute(HttpServletRequest req,
//                                  HttpServletResponse resp) throws IOException, ServletException {
//                return homeController.getIndex(req, resp);
//            }
//        });

//        getMap.put("/todo/list", todoController::getList);
//        getMap.put("/todo/view", todoController::getView);
//        getMap.put("/todo/create", todoController::getCreate);
//        getMap.put("/todo/update", todoController::getUpdate);

        // POST 요청에 대한 경로와 처리 메서드 등록
//        postMap.put("/todo/create", todoController::postCreate);
//        postMap.put("/todo/update", todoController::postUpdate);
//        postMap.put("/todo/delete", todoController::postDelete);
    }

    // 공통된 커맨드 실행 로직
    public void execute(Command command, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // 커맨드 실행 결과로 받은 뷰 이름
        String viewName = command.execute(req, resp);

        // "redirect:" 접두사로 시작하면 리다이렉트 처리
        if (viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring("redirect:".length()));
        } else {
            // 그 외는 JSP 포워드 처리
            String view = prefix + viewName + suffix;
            RequestDispatcher dis = req.getRequestDispatcher(view);
            dis.forward(req, resp);
        }
    }

    // GET 요청 처리
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 요청에 해당하는 커맨드 객체 조회
        Command command = getCommand(req);

        if (command != null) {
            execute(command, req, resp); // 정상 처리
        } else {
            // 정의되지 않은 URL이면 404 페이지로 이동
            String view = prefix + "404" + suffix;
            RequestDispatcher dis = req.getRequestDispatcher(view);
            dis.forward(req, resp);
        }
    }

    // POST 요청도 GET처럼 처리 (일부 방식에서는 분리 권장됨)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    // 전체 URI에서 ContextPath 이후의 경로만 추출
    private String getCommandName(HttpServletRequest req) {
        String requestURI = req.getRequestURI();     // 예: /myapp/todo/list
        String contextPath = req.getContextPath();   // 예: /myapp
        return requestURI.substring(contextPath.length()); // 예: /todo/list
    }

    // HTTP 메서드에 따라 GET/POST 맵에서 커맨드 조회
    private Command getCommand(HttpServletRequest req) {
        String commandName = getCommandName(req); // ===> /todo/list

        Command command;

        //Get/Post 중 어떤 메서드인지 판별해서
        //Get이면 GetHash에서 어떤 함수를 불러야하는지 알아오고,
        //Post이면 PostHash에서 어떤 함수를 불러야하는지 알아오자.
        if (req.getMethod().equalsIgnoreCase("GET")) {
            command = getMap.get(commandName);
        } else {
            command = postMap.get(commandName);
        }

        return command;
    }
}
