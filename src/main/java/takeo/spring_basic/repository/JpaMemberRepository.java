package takeo.spring_basic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import takeo.spring_basic.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member as m", Member.class).getResultList().stream().filter(m -> {
            return m.getName().equals(name);
        }).findAny();
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member as m", Member.class).getResultList();
    }

    @Override
    public void clearStore() {

    }
}
