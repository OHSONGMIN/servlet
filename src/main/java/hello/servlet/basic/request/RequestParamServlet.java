package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns="/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override //인텔리제이에선 ctrl+O가 아닌 service해서 불러와도 됨
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
//라고 하면 paramName 출력 가능
        //paramName은 이름을 꺼내는 것. 즉 username, age
        //request.getParameter(paramNAame) 은 값을 꺼낼 수 있다 hello, 20

        Enumeration<String> parameterNames = request.getParameterNames();//라고 하면 모든 요청 파라미터를 꺼낼 수 있다(username, name)

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] - end");

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        //iter + enter 치면 아래와 같은 반복문 얻을 수 있음
        for (String name : usernames) {
            System.out.println("username = " + name);

        }

        response.getWriter().write("ok");
    }

}