package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

public interface MyHandlerAdapter {

    //내가 해당 핸들러(컨트롤러)를 지원할 수 있는지
    boolean supports(Object handler);

    ModelView handle(HttpServletResponse response, HttpServletRequest request, Object handler) throws ServerCloneException, IOException;
}
