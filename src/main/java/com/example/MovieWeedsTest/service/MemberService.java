package com.example.MovieWeedsTest.service;

import com.example.MovieWeedsTest.config.jwt.JwtTokenProvider;
import com.example.MovieWeedsTest.config.jwt.dto.TokenDto;
import com.example.MovieWeedsTest.config.jwt.ex.CustomException;
import com.example.MovieWeedsTest.domain.Member;
import com.example.MovieWeedsTest.dto.RequestSignInMember;
import com.example.MovieWeedsTest.dto.RequestSignUpMember;
import com.example.MovieWeedsTest.repository.MemberGenreRepository;
import com.example.MovieWeedsTest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberGenreRepository memberGenreRepository;
    private final AuthenticationManager authenticationManager;

    public void newMemberCreate(RequestSignUpMember requestSignUpMember) {
        memberRepository.save(new Member(requestSignUpMember, bCryptPasswordEncoder));
    }


    public TokenDto signInService(RequestSignInMember requestSignInMember) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestSignInMember.getEmail(), requestSignInMember.getPassword()));
            return new TokenDto(jwtTokenProvider.generateToken(authenticate));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid credentials supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
