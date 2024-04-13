package graduation.petshop.domain.member.dto.response;

import graduation.petshop.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseFindPwdDto {

    private String passWard;

    public ResponseFindPwdDto(Member m){
        this.passWard = m.getPassword();
    }
}
