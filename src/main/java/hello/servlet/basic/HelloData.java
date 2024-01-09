package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //라고 하면 getter setter를 직접 만들지 않아도 자동으로 만들어진다.
public class HelloData {

    private String username;
    private int age;

}
