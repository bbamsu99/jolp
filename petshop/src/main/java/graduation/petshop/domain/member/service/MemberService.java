package graduation.petshop.domain.member.service;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * 중복조회 필요한가?
     */

    // (김범수) 중복 조회 - 저장된 이메일이 존재하는 지 확인

    public String duplicationCheck(String CheckingEmail){

        if(memberRepository.findByEmail(CheckingEmail) != null ){       // 이거 쿼리 조회 안 되면 null 맞아??
            return null;               //  중복 시 null 반환
        }

        return CheckingEmail; // 중복된 email이 아니면 그 email 반환
    }


    //


    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }



    /**
     * 로그인 TODO 기능만들기
     * 아이디와 패스워드가 일치하지 않으면 null 반환
     */
    public Member login(String loginId, String password){
        Member member = memberRepository.findByLoginId(loginId);
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;

    }


    /**
     * 이메일로 아이디 찾기
     *
     */
    public Member findIdByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    /**
     * 로그인아이디로 비밀번호 찾기
     */
    public Member findPwdByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }


}
