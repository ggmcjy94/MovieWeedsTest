package com.example.MovieWeedsTest.controller;

import com.example.MovieWeedsTest.config.jwt.dto.TokenDto;
import com.example.MovieWeedsTest.dto.RequestSignInMember;
import com.example.MovieWeedsTest.dto.RequestSignUpMember;
import com.example.MovieWeedsTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createMemberController (@Validated @RequestBody RequestSignUpMember requestSignUpMember) {
        memberService.newMemberCreate(requestSignUpMember);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signInMemberController (@Validated @RequestBody RequestSignInMember requestSignInMember) {
        return new ResponseEntity<>(memberService.signInService(requestSignInMember), HttpStatus.OK);
    }
}
