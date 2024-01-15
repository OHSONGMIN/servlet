package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletResponse response, HttpServletRequest request, Object handler) throws ServerCloneException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        //ControllerV4의 process는 paramMap과 model을 받는다 어떻게 할까
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        //return viewName; 를 하고자 했는데 반환타입이 안맞는구나
        //어댑터 출동

        //ModelView를 어댑터에서 생성
        ModelView mv = new ModelView(viewName); //모델뷰이기 때문에 뷰도 세팅해주고
        mv.setModel(model); //모델도 세팅해준다.

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
