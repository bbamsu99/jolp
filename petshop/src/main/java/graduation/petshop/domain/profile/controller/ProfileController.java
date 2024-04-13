package graduation.petshop.domain.profile.controller;

import graduation.petshop.domain.profile.dto.request.RequestCreateProfileDto;
import graduation.petshop.domain.profile.dto.request.RequestUpdateProfileDto;
import graduation.petshop.domain.profile.dto.response.ResponseFindProfileDto;
import graduation.petshop.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    // post -> 프로필 생성 메소드
    @PostMapping("/{memberId}")
    public ResponseEntity<Long> createProfile(@PathVariable Long memberId, @RequestBody @Validated RequestCreateProfileDto requestDto) {
        log.info("프로필 생성 {}", memberId);
        Long profileId = profileService.createProfile(memberId, requestDto);
        log.info("프로필 생성완료. {}", profileId);
        return ResponseEntity.ok(profileId);
    }

    // get -> 프로필 조회 메소드
    @GetMapping("/{profileId}")
    public ResponseEntity<ResponseFindProfileDto> getProfile(@PathVariable Long profileId) {
        log.info("프로필 조회 {}", profileId);

        ResponseFindProfileDto profileDto = profileService.getProfileById(profileId);

        if (profileDto == null) {
            log.error("프로필을 찾을 수 없습니다. {}", profileId);
            return ResponseEntity.notFound().build();
        }

        log.info("프로필 조회 {}", profileDto);
        return ResponseEntity.ok(profileDto);
    }

    // put -> 프로필 업데이트 메소드
    @PutMapping("/{profileId}")
    public ResponseEntity<Long> updateProfile(@PathVariable Long profileId, @RequestBody @Validated RequestUpdateProfileDto requestDto) {
        log.info("프로필 ID 업데이트 {}", profileId);
        profileService.updateProfile(profileId, requestDto);
        log.info("업데이트 완료.");
        return ResponseEntity.ok().build();
    }
}
