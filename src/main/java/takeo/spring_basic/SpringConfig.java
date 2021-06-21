package takeo.spring_basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import takeo.spring_basic.aop.TimeTraceAop;
import takeo.spring_basic.repository.*;
import takeo.spring_basic.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // jdbc, jdbc templatesを使用するためには必要
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private final DataSource dataSource;
//    private final EntityManager entityManager;
//
//    public SpringConfig(DataSource dataSource, EntityManager entityManager) {
//        this.dataSource = dataSource;
//        this.entityManager = entityManager;
//    }

    // spring data jpaがbeanとして追加したものを自動注入
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcTemplateRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }
}
