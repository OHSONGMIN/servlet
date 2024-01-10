package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Test는 public 없어도 됨
class MemberRepositoryTest {

    //먼저 테스트할 대상을 가져온다
    //MemberRepository memberRepository = new MemberRepository() 처럼 new 하면 안된다 -> 싱글톤이기 때문에
    MemberRepository memberRepository = MemberRepository.getInstance();
    //스프링 쓰면 싱글톤 쓸 필요X -> 스프링 자체가 싱글톤을 보장해주기 떄문에

    //테스트가 끝날 때마다 초기화 하기 위해
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given 이런 게 주어졌을 때
        Member member = new Member("hello", 20);

        //when 이런 걸 실행했을 때
        Member savedMember = memberRepository.save(member);

        //then 결과가 이거여야 돼
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember); //찾아온 멤버는 저장된 멤버와 같아야 한다.

    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 30);
        Member member2 = new Member("member2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        //alt + enter 눌러서 static import
        //Assertions.assertThat(result.size()).isEqualTo(2);
        assertThat(result.size()).isEqualTo(2); //result의 size는 2개가 되어야함

        assertThat(result).contains(member1, member2); //result 안에 member1, member2가 있는지 확인
    }

}
