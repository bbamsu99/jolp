package graduation.petshop.domain.profile.entity;
import graduation.petshop.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "profile")
@Getter
public class Profile {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Gender sex; // 성별 [FEMALE, MALE]

    @Enumerated(EnumType.STRING)
    private PetStatus petStatus; // 반려동물 유무 [PETYES, PETNO]

    private Integer age;

    

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPetStatus(PetStatus petStatus){
        this.petStatus = petStatus;
    }

}

