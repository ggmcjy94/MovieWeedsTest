package com.example.MovieWeedsTest.controller;

import com.example.MovieWeedsTest.config.jwt.dto.TokenDto;
import com.example.MovieWeedsTest.dto.request.RequestAuthMember;
import com.example.MovieWeedsTest.dto.request.RequestMemberMovieGrade;
import com.example.MovieWeedsTest.dto.request.RequestRecommendGenres;
import com.example.MovieWeedsTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createMemberController (@Validated @RequestBody RequestAuthMember requestSignUpMember) {
        memberService.newMemberCreate(requestSignUpMember);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signInMemberController (@Validated @RequestBody RequestAuthMember requestAuthMember) {
        return new ResponseEntity<>(memberService.signInService(requestAuthMember), HttpStatus.OK);
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> createRecommendedGenreController(@AuthenticationPrincipal UserDetails userDetails,
                                                              @RequestBody RequestRecommendGenres genres_id) {
        memberService.createRecommendedGenre(genres_id, userDetails);
        return ResponseEntity.ok().build();
    }




}
