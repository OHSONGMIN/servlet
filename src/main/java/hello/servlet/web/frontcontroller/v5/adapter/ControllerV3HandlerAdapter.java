package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return(handler instanceof ControllerV3); //handler가 ControllerV3의 instance야?
    }

    @Override
    public ModelView handle(HttpServletResponse response, HttpServletRequest request, Object handler) throws ServerCloneException, IOException {
        //유연하게 해야하기 때문에 Object로.. 그리고 ControllerV3로 타입 변환
        ControllerV3 controller = (ControllerV3) handler;

        //ControllerV3는 process 하려면 Map이 필요하다 paramMap
        //paramMap 바꾸는 코드가 필요하겠다
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }

}
