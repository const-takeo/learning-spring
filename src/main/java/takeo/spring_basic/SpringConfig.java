package takeo.spring_basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import takeo.spring_basic.repository.JdbcTemplateRepository;
import takeo.spring_basic.repository.MemberRepository;
import takeo.spring_basic.repository.MemoryMemberRepository;
import takeo.spring_basic.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // jdbc, jdbc templatesを使用するためには必要
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcTemplateRepository(dataSource);
    }
}
