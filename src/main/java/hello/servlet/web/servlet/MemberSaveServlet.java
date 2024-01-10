package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    //회원 저장하려면 MemberRepository가 필요함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        //HTML Form에서 온 것을 getParameter를 이용해서 꺼낸다.
        String username = request.getParameter("username"); //GET 쿼리 파라미터 방식이든 HTML Form 전송 방식이든 request.getParameter로 꺼낼 수 있다.

        //request.getParameter의 반환 타입은 항상 문자이다 -> 나이를 꺼낼 땐 숫자 타입으로 변환해야
        int age = Integer.parseInt(request.getParameter("age"));

        //그리고 멤버 객체를 만들자
        Member member = new Member(username, age); //생성자 기억하지?
        memberRepository.save(member); //저장하기

        //저장 잘 됐는지 확인(결과를 html로 응답)
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
