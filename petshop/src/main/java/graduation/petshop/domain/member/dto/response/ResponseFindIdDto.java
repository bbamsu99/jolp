package graduation.petshop.domain.member.dto.response;

import graduation.petshop.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseFindIdDto {

    private String loginId;

    public ResponseFindIdDto(Member m){
        this.loginId = m.getLoginId();
    }
}
