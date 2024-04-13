package graduation.petshop.domain.member.controller;

import graduation.petshop.domain.email.EmailController;
import graduation.petshop.domain.member.dto.request.JoinDto;
import graduation.petshop.domain.member.dto.request.LoginDto;
import graduation.petshop.domain.member.dto.request.RequestFindIdDto;
import graduation.petshop.domain.member.dto.request.RequestFindPwdDto;
import graduation.petshop.domain.member.dto.response.ResponseFindIdDto;
import graduation.petshop.domain.member.dto.response.ResponseFindPwdDto;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * service에 회원가입 안 넣고 Controller에 넣었다.
     * 분리가 필요하다 생각함
     */
    @PostMapping("/member/add")
    public ResponseEntity<Object> joinMember(@RequestBody @Valid JoinDto joinDto){

        Member member = joinDto.toEntity(
                joinDto.getLoginId(),
                joinDto.getPassword(),
                joinDto.getEmail()
        );

        //김범수

        if (memberService.duplicationCheck(member.getEmail()) != null){          // 중복검사

//            EmailController;

            if(EmailController.checkingEmailFirst(member) == true){
                log.info("이메일 전달 완료");
                if(EmailController.checkingEmailSecond(member)==true) {
                    memberService.join(member);
                    log.info("회원가입 완료");
                    return ResponseEntity.ok("ok");

                }
            }
        }
        return ResponseEntity.badRequest().build(); // 오류 메세지 이게 맞나 확인을 어떻게 하죠?

        //

    }

    /**
     * 아이디찾기
     *
     */
    @GetMapping("/member/find-id")
    public ResponseFindIdDto findByEmail(@RequestBody @Valid RequestFindIdDto requestFindIdDto){
        Member member = memberService.findIdByEmail(requestFindIdDto.getEmail());
        return new ResponseFindIdDto(member);
    }

    /**
     * 비밀번호찾기
     *
     */
    @GetMapping("/member/find-pwd")
    public ResponseFindPwdDto findByLoginId(@RequestBody @Valid RequestFindPwdDto requestFindPwdDto){
        Member member = memberService.findPwdByLoginId(requestFindPwdDto.getLoginId());
        return new ResponseFindPwdDto(member);
    }

    /**
     * 로그인
     *
     */
    @GetMapping("/member/login")
    public ResponseEntity<Object> memberLogin(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Member member = memberService.login(loginDto.getLoginId(), loginDto.getPassword());

        if(member == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        log.info("로그인 성공");
        return ResponseEntity.ok("로그인 성공");
    }
}
