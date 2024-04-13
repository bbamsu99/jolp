package graduation.petshop.domain.profile.service;

import graduation.petshop.domain.profile.dto.request.RequestCreateProfileDto;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.dto.request.RequestUpdateProfileDto;
import graduation.petshop.domain.profile.dto.response.ResponseFindProfileDto;
import graduation.petshop.domain.profile.entity.Profile;
import graduation.petshop.domain.member.repository.MemberRepository;
import graduation.petshop.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createProfile(Long memberId, RequestCreateProfileDto requestDto) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);

        // 프로필 생성
        Profile profile = new Profile();
        profile.setNickName(requestDto.getNickName());
        profile.setSex(requestDto.getSex());
        profile.setAge(requestDto.getAge());
        profile.setPetStatus(requestDto.getPetStatus());

        // 프로필 저장
        profileRepository.save(profile);

        // 생성된 프로필의 ID 반환
        return profile.getId();
    }

    @Transactional
    public void updateProfile(Long profileId, RequestUpdateProfileDto requestDto) {
        Profile profile = profileRepository.findById(profileId);
        if (profile == null) {
            throw new IllegalArgumentException("프로필을 찾을 수 없습니다. 프로필 ID: " + profileId);
        }
        // 요청된 정보로 프로필 업데이트
        profile.setNickName(requestDto.getNickName());
        profile.setSex(requestDto.getSex());
        profile.setAge(requestDto.getAge());
        profile.setPetStatus(requestDto.getPetStatus());

        // 변경된 프로필 저장
        profileRepository.save(profile);
    }



    public ResponseFindProfileDto getProfileById(Long profileId) {
        Profile profile = profileRepository.findById(profileId);
        if (profile == null) {
            return null;
        }

        ResponseFindProfileDto profileDto = new ResponseFindProfileDto();
        profileDto.setNickName(profile.getNickName());
        profileDto.setSex(profile.getSex());
        profileDto.setAge(profile.getAge());
        profileDto.setPetStatus(profile.getPetStatus());

        return profileDto;
    }
}
