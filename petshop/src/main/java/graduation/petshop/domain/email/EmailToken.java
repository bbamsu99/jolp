package graduation.petshop.domain.email;

import graduation.petshop.domain.member.entity.Member;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class EmailToken {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L; // 제한시간

    private LocalDateTime expirationDate;
//    private boolean expired;
    private Member tokenmember;

    public static EmailToken createEmailToken(Member member) {
        EmailToken emailToken = new EmailToken();
        emailToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
//        emailToken.expired = false;
        emailToken.tokenmember = member;

        return emailToken;
    }


}
