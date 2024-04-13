package graduation.petshop.domain.member.dto.request;

import graduation.petshop.domain.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

}
