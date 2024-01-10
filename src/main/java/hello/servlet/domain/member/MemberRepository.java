package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository { //ctrl + shift + T 하면 Test 코드 만들 수 있다

    private static Map<Long, Member> store = new HashMap<>(); //key는 ID, value는 Member
    private static long sequence = 0L; //ID가 하나씩 증가하는 시퀀스
    //static으로 했기 때문에 엥버 리포지토리가 아무리 new해도 딱 하나만 사용된다.

    //톰캣 띄울 때만 스프링 컨테이너 쓰는 거고 그 외에는 안쓸 예정 -> 싱글톤으로 만든다
    private static final MemberRepository instance = new MemberRepository();

    //싱글톤으로 만들 땐 아무나 생성못하게 private으로 생성자를 막아야한다.
    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return instance;
    } //얘로 조회하도록

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 모든 값들을 꺼내서 새로운 ArrayList에 담아서 넘겨준다.
    }

    //store 날려버리는 메서드
    public void clearStore() {
        store.clear();
    }



}
