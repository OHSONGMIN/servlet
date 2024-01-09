package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override //protexted service를 호출해야함(publicX)
    //extends HttpServlet 후 ctrl + O 하고 service를 만든다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request); //ctrl + T 이용해서 메서드 추출
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        //http 요청 메시지에 가장 첫 라인에 있는 정보를 불러옴
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET, http 메서드
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header //전체 url
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header //uri는 뒤에 것 호출
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " +
                request.getQueryString()); //없으니까 null
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용유무 //https인가 아닌가
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보 가져오기
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
/*
 Enumeration<String> headerNames = request.getHeaderNames();
 while (headerNames.hasMoreElements()) {
 String headerName = headerNames.nextElement();
 System.out.println(headerName + ": " + request.getHeader(headerName));
 }
*/
        Enumeration<String> headerNames = request.getHeaderNames();//는 옛날 방식 // http 요청 메시지에 있는 모든 헤더 정보를 다 꺼내서 출력할 수 있음
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName+ " 옛날 : " + headerName);
        }
//        request.getHeaderNames().asIterator()
//                .forEachRemaining(headerName -> System.out.println(headerName + ": "
//                        + request.getHeader(headerName)));
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + " 요즘 : " + headerName));

        //header정보 딱 하나만 조회하고 싶을 때
        //request.getHeader("host");

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회 //이런 기능이 있다
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale()); //라고 하면 제일 위에 있는 것이 자동으로 뽑힌다. 제일 선호하는 언어를 자동으로 뽑는다.
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType()); //get은 컨텐트를 거의 안보내기 때문에 null 잘 나옴...
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding()); //인코딩 정보
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();

    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
