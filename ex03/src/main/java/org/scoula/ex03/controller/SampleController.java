package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller //싱글톤 + 클래스안에서 설정한 주소->함수 매핑을 핸들러매퍼에게 등록시켜줌.
//@Component + 컨트롤러 기능도 추가.
@RequestMapping("/sample") //해당 도메인을 요청할 때는 앞에다 sample이라고 붙이자.!
//http://localhost:8080/sample/insert, localhost == 127.0.0.1
// /sample/delete, /sample/update
@Log4j2
public class SampleController {
    
    //sample 도메인을 위한 여러 요청에 대한 함수를 정의
    @RequestMapping("") //sample
    public String sample() {
        log.info("SampleController =================================");
        return "sample";
        // /WEB-INF/views/sample.jsp ==> 없는 경우 404가 발생함.
    }

    @GetMapping("/basicOnlyGet")
    public void basicOnlyGet() {
        //void인 경우에 호출한 주소를 사용해서 /WEB-INF/views/요청주소.jsp
        //요청주소와 views밑에 파일이름이 같다면 String으로 return안해도 됨.
        // /sample/basicOnlyGet.jsp를 찾음.
        log.info("/basicOnlyGet ==============");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    // /sample/basic
    public void basic(){
        log.info("/basic ===============");
        //스프링은 컨트롤러로 오면 그 다음은 무조건 jsp파일을 호출하는 흐름임.
        //forward라고 함. ==> 결과를 넣는 jsp페이지가 필요할 때!
        //redirect해서 view로 안갈수도 있음. ==> 다른 페이지로 전환
    }

    @GetMapping("/ex01")
    public String ex01(String name,
                       @RequestParam("age") int age2){
        // @RequestParam: 파라메터와 다른 변수에 넣고 싶은 경우
        // @RequestParam("파라메터이름") int 넣고싶은변수이름
        
        //원래 http로 전달받은 데이터는 모두 String
        //int age라고 하는 경우 이것을 숫자로 변경해주는 작업도
        //스프링이 해준다.

// http://localhost:8080/sample/ex01?name=apple&age=100
//        String name = request.getParameter("name");
//        int age = Integer.parseInt(request.getParameter("age"));
        // ==> 개발 성능이 좋아짐.
        log.info("/ex01 =================");

        System.out.println("name: " + name);
        System.out.println("age: " + (age2 + 1));
        return "ex01";
    }


    @GetMapping("/ex011")
    //전달되는 데이터가 많은 경우 가방역할의 변수에 넣어달라고 요청하면 됨.
    //dto에 있는 파라메터와 동일한 set메서드를 불러서 가방에 넣어줌.
    //컨트롤러 함수 호출시 입력파라메터로 넣은 dto는 자동으로
    //view까지 전달됨. Model의 속성으로 추가가 됨.
    //주의점!! 변수명을 첫글자만 클래스이름과 다르게 소문자로 만들어야함.
    //model.addAttribute("sampleDTO", sampleDTO);
    public String ex011(SampleDTO sampleDTO,
                        @ModelAttribute("day") String day){
        System.out.println("name: " + sampleDTO.getName());
        System.out.println("age: " + (sampleDTO.getAge() + 1));
        return "ex011";
    }

    //json으로 응답, views아래 jsp를 사용하지 않는 경우
    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {
        log.info("/ex07........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }

    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename()); // 윈도우 OS: 한글 파일명인 경우 깨짐
            log.info("size:" + file.getSize());
        }
    }
}
