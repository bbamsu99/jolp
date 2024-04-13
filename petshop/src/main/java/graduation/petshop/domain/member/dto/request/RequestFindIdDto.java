package graduation.petshop.domain.member.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestFindIdDto {

    @NotEmpty
    private String Email;

}
