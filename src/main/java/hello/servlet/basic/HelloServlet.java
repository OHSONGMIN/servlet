package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // /hello로 오면 실행될 것이다.
public class HelloServlet extends HttpServlet { //서블릿은 HttpServlet을 상속받아야한다

    @Override //servlet이 호출되면 이 서비스 메서드도 호출된다, ctrl+O 사용해서 메서드 만듦
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); //soutm +enter 하면 클래스면.메서드면 나온다
//http 요청이 오면 서블릿 컨테이너(WAS)가 request, response 객체를 만들어서 서블릿에 던져준다고 한거 기억하지?
//즉 localhost:8080/hello을 호출하면 웹 브라우저가 http 요청메시지를 만들어서 서블릿에 던진다
        System.out.println("request = " + request);
        System.out.println("response = " + response); //soutv+enter 단축키

        String username = request.getParameter("username"); //ctrl + alt + v 해서 자동 완성,, 값 꺼내와서... 변수에 담아
        System.out.println("username = " + username);

//그리고 응답은 HttpServletResponse에 찍어줘야 한다.
        response.setContentType("text/plain"); //단순 문자로 보내고
        response.setCharacterEncoding("utf-8"); //인코딩 정보를 알려준다
        response.getWriter().write("hello " + username); //write라고 하면 http 메시지 바디에 데이터가 들어간다.
        //그리고 response.setContentType("text/plain"); response.setCharacterEncoding("utf-8"); 이 두개는 ContentType 헤더 정보에 들어간다.




    }
}
