package org.scoula.ex03.command2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}

