package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    //1. HttpServlet 상속
    //2. service 메소드 오버라이드
    //3. RequestBodyStringServlet 클래스에 @WebServlet 어노테이션 작성

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        //라고 하면 메시지 바디의 내용을 byte 코드로 바로 얻을 수 있다.
        //를 spring으로 바꾸기 위해서
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스프링이 제공하는 유틸리티
//항상 byte를 문자로 변환할 땐 어떤 인코딩인지 알려줘야하고
        //문자를 byte로 바꿀 때도 어떤 인코딩인지 알려줘야 함
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");


    }
}
