package takeo.spring_basic.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import takeo.spring_basic.domain.Member;

public class MemmoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void save() throws Exception {
        //given
        Member member = new Member();
        member.setName("TestMember1");
        //when
        memberRepository.save(member);
        Member savedMember = memberRepository.findById(member.getId()).orElseThrow();
        //then
        Assertions.assertThat(member).isEqualTo(savedMember);
        Assertions.assertThat(member.getName()).isEqualTo(savedMember.getName());
    }

    @Test
    void findByName() throws Exception {
        //given
        Member member = new Member();
        member.setName("TestUser2");
        //when
        memberRepository.save(member);
        Member foundedMember = memberRepository.findByName("TestUser2").orElseThrow();
        //then
        Assertions.assertThat(foundedMember.getName()).isEqualTo(member.getName());

    }

    @Test
    void findAll() throws Exception {
        //given
        Member member = new Member();
        member.setName("TestUser3");
        memberRepository.save(member);

        Member member1 = new Member();
        member1.setName("TestUser4");
        memberRepository.save(member1);

        //when
        int size = memberRepository.findAll().size();

        //then
        Assertions.assertThat(size).isEqualTo(2);

    }
}
