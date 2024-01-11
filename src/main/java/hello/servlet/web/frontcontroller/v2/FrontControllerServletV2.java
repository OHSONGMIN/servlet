package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
//v2 하위에 어떤 url요청이 들어와도 일단 이 서블릿을 무조건 호출한다.
public class FrontControllerServletV2 extends HttpServlet {

    //url(key)이 들어오면 해당되는 ControllerV2(value)을 호출해라
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //서블릿이 생성될 떄 매핑 정보를 담는다.
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    } //미리 저장해두면 꺼내서 쓸 수 있음

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("frontControllerServletV2.service");

        String requestURI = request.getRequestURI(); //예를 들어 /front-controller/v2/members/new-form 와 같다

        //인터페이스로 꺼내면 아래 코드처럼 일관성 있게 사용할 수 있다
        ControllerV2 controller = controllerMap.get(requestURI); //MemberFormControllerV2()가 반환될 것이다
        //위는 ControllerV2 controller = new MemberFormControllerV2() 과 같다.

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //404인 경우에 바로 return
        }

        //조회가 잘되면 controller.process 인터페이스 호출
        //이때의 controller는 MemberFormControllerV1라는 controller일 것이다.
        //다형성에 의해서 오버라이드된 메서드 호출될 것이다
        MyView view = controller.process(request, response);
        //new MyView("/WEB-INF/views/new-form.jsp")
        view.render(request, response);

    }
}
