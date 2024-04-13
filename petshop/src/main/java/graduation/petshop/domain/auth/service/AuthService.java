package graduation.petshop.domain.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.client_secret}")
    private String client_secret;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;


    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    /**
     * 카카오 로그인 페이지 만들기
     */

    public String KakaoLoginPage(){
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + client_id
                + "&redirect_uri=" + redirect_uri
                + "&response_type=code";
    }
}
