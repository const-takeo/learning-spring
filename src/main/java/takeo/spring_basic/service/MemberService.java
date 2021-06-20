package takeo.spring_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import takeo.spring_basic.domain.Member;
import takeo.spring_basic.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException(m.getName() + "メンバーが既に存在します。");
        });
        //OptionalのifPresentは使わないほうがいい
//        memberRepository.findByName(member.getName()).orElseThrow(() -> {
//            throw new IllegalStateException(member.getName() + "メンバーが既に存在します。");
//        });

    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
