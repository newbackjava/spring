package org.scoula.ex03.controller2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController {
    public String getIndex(HttpServletRequest req,
                           HttpServletResponse resp) throws IOException {
        return "index";
    }
}
