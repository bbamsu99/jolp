package graduation.petshop.domain.auth.controller;

import graduation.petshop.domain.auth.dto.response.LoginPageUriDto;
import graduation.petshop.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/kakao/page")
    public LoginPageUriDto kakaoLoginPage(){
        String page = authService.KakaoLoginPage();
        return new LoginPageUriDto(page);
    }

}
