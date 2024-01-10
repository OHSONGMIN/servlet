package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //lombok의 getter, setter
public class Member {

    private Long id; //Member를 회원 저장소에 저장하면 회원 저장소가 자동으로 id를 할당할 것
    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member() { //기본 생성자
    }
}
