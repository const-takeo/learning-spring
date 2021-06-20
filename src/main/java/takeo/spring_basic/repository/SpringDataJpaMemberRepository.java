package takeo.spring_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import takeo.spring_basic.domain.Member;

import java.util.Optional;

//spring data jpaが自動的にcontainerにbeanとして追加する。
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
