package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
//v3 하위에 어떤 url요청이 들어와도 일단 이 서블릿을 무조건 호출한다.
public class FrontControllerServletV3 extends HttpServlet {

    //url(key)이 들어오면 해당되는 ControllerV3(value)을 호출해라
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    //서블릿이 생성될 떄 매핑 정보를 담는다.
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    } //미리 저장해두면 꺼내서 쓸 수 있음

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("frontControllerServletV3.service");

        String requestURI = request.getRequestURI(); //예를 들어 /front-controller/v3/members/new-form 와 같다

        //인터페이스로 꺼내면 아래 코드처럼 일관성 있게 사용할 수 있다
        ControllerV3 controller = controllerMap.get(requestURI); //MemberFormControllerV3()가 반환될 것이다
        //ControllerV3 controller = new MemberFormControllerV3() 과 같다.

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //404인 경우에 바로 return
        }

        //ctrl + alt + M 으로 메서드로 뽑는다
        Map<String, String> paramMap = createParamMap(request);
        //key 변수명을 paramName으로 잡고 value는 request.getParameter(paramName) 로 모든 parameter를 다 꺼내온다.
        ModelView mv = controller.process(paramMap);//그 다음에 짠 넣어주면 됨

        String viewName = mv.getViewName(); //논리이름 new-form

        // 물리이름 "/WEB-INF/views/new-form.jsp"으로 MyView 반환
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() //모든 파라미터 이름을 다 가져오고 돌리면서
                .forEachRemaining(paramName ->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
