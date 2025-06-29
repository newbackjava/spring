package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j2
@Order(1)
public class CommonExceptionAdvice {

//    @ExceptionHandler(Exception.class)
//    public String except(Exception ex, Model model) {
//
//        log.error("Exception ......." + ex.getMessage());
//        model.addAttribute("exception", ex);
//        log.error(model);
//        return "error_page";
//    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex) {
        log.error(ex);
        return "/resources/index.html";
    }

}