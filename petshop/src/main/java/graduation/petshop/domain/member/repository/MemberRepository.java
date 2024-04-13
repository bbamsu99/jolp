package graduation.petshop.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import graduation.petshop.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //멤버 저장
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    //아이디로 찾기
    public Member findById(Long id){
        return em.find(Member.class,id);
    }

    //로그인 아이디로 찾기
    public Member findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class).setParameter("loginId", loginId).getSingleResult();
    }

    //이메일로 찾기
    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class).setParameter("email", email).getSingleResult();
    }
    //전체 찾기
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }




}
