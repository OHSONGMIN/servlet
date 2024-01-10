package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns ="/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//Controller에서 view로 이동할 떄 사용
        dispatcher.forward(request, response); //를 호출하면 이제 Servlet에서 JSP를 호출할 수 있다.
        //dispatcher.forward는 다른 Servlet이나 JSP로 이동할수있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
        //redirect는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청한다. 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. → 클라이언트 입장에서 보면 호출이 두 번 일어나는 것
        //forward는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.  → 클라이언트 입장에서 보면 한번 호출하고 바로 응답 받은 것
    }
}
