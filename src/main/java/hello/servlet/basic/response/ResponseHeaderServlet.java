package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
//request와 마찬가지로 1. HttpServlet 상속
    //2. service 메소드 오버라이딩
    //3. @WebServlet 어노테이션 부여
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //[status-line]
        //response.setStatus(200); //성공하면 기본이 200, 직접 200이라고 적는 것보다 상수로 쓰는 것이 나음
        response.setStatus(HttpServletResponse.SC_OK);
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //캐시를 무효화 하겠다는 뜻
        response.setHeader("Pragme", "no-cache"); //과거 버전까지 캐시를 없애는 것
        response.setHeader("my-header", "hello"); //내가 원하는 헤더를 만들수도 있음

        //[Header 편의 메소드]
        //content(response);
        //cookie(response);
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요"); //원하는 값이 message-body에 넣을 수 있다.


    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) { //cookie import 잘 선택하기
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good"); //Cookie객체를 만들어서
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie); //cookie를 넣는다
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //와 같은 결과를 얻기 위해 아래 2줄 처럼 작성

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        response.sendRedirect("/basic/hello-form.html"); // 위 두줄과 같은 역할을 한다
    }
}
