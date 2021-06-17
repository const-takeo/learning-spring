package takeo.spring_basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import takeo.spring_basic.repository.MemberRepository;
import takeo.spring_basic.repository.MemoryMemberRepository;
import takeo.spring_basic.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
