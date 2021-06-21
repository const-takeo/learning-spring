package takeo.spring_basic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import takeo.spring_basic.domain.Member;
import takeo.spring_basic.repository.MemberRepository;
import takeo.spring_basic.repository.MemoryMemberRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    // Testで使用するrepositoryと実際のServiceで使用するrepositoryをDIを利用して一致させる。
    MemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {

    }

    @Test
    void join() throws Exception {
        long start = System.currentTimeMillis();
        try {

            //given
            Member member = new Member();
            member.setName("TestMember1");
            //when
            Long joinMemberId = memberService.join(member);
            //then
            memberService.findOne(joinMemberId).ifPresent(member1 -> {
                Assertions.assertThat(member1).isEqualTo(member);
            });
        }finally {
            long finalTime = System.currentTimeMillis();
            long executeTime = finalTime - start;
            System.out.println("executeTime = " + executeTime);
        }
    }

    @Test
    void findeMembers() throws Exception {
        //given
        Member member = new Member();
        member.setName("TestMember1");
        memberService.join(member);

        Member member1 = new Member();
        member1.setName("TsetMember2");
        memberService.join(member1);
        //when
        List<Member> members = memberService.findMembers();
        //then
        Assertions.assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void validateDuplicateMember() throws Exception {
        //given
        Member member = new Member();
        member.setName("TestUser");
        memberService.join(member);

        Member member1 = new Member();
        member1.setName("TestUser");
        //when
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        //then
        Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("TestUserメンバーが既に存在します。");
        Assertions.assertThat(memberService.findMembers().size()).isEqualTo(1);
    }
}