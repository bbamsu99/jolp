package graduation.petshop.domain.email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
@Getter
public class EmailTokenService {

    private static EmailSenderService emailSenderService;


    // 이메일 인증 토큰 생성
    public static void sendEmailToken(EmailToken emailToken, String key) {
        // 이메일 전송
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailToken.getTokenmember().getEmail());
        mailMessage.setSubject("회원가입 이메일 인증");


        mailMessage.setText("인증번호는 ( " + key + " ) 입니다.");

        emailSenderService.sendEmail(mailMessage);
        log.info("메일 전송 완료");

    }

    //인증번호 random 함수 분리 필요??
    public static String createKey() {
        StringBuilder key = new StringBuilder(); //Stringbuffer?
        Random random = new Random();

        for (int i = 0; i < 10; i++) { // 인증코드 10자리
            int index = random.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((random.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }




}
