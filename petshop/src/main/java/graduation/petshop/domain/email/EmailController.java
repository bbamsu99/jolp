package graduation.petshop.domain.email;

import graduation.petshop.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class EmailController {

    static boolean emailconfiming = false; // 이메일 인증 완료 시, true로 변환
    private static EmailToken emailToken;
    private static String key = EmailTokenService.createKey();

    public static boolean checkingEmailFirst(Member member) {

        emailToken = EmailToken.createEmailToken(member);
        EmailTokenService.sendEmailToken(emailToken, key);

        return true;
    }

    public static boolean checkingEmailSecond(Member member){
        LocalDateTime date1 = emailToken.getExpirationDate();
        LocalDateTime date2 = LocalDateTime.now();
        boolean result = date1.isBefore(date2);

        if ( result == true ) {
            if (key == member.getUserInsertedKey()) {
                emailconfiming = true;
            }
        }

        return emailconfiming;
    }
}
