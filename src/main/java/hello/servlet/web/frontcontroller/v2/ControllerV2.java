package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    //ControllerV1과 똑같지만 반환 타입은 MyView로 설정한다.
    MyView process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
