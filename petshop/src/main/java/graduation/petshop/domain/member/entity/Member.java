package graduation.petshop.domain.member.entity;

import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String loginId;

    private String password;

    private String imageUrl;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    // Member 엔티티와 Profile 엔티티 간의 일대일 관계 설정
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
