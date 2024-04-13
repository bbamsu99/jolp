package graduation.petshop.domain.member.dto.request;

import graduation.petshop.domain.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;

    /**
     * DTO 안에서 member객체로 변환
     */
    public Member toEntity(String loginId, String password, String email){
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .build();
    }

}
