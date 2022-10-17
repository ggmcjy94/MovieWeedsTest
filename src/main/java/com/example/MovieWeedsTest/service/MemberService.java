package com.example.MovieWeedsTest.service;

import com.example.MovieWeedsTest.config.jwt.JwtTokenProvider;
import com.example.MovieWeedsTest.config.jwt.dto.TokenDto;
import com.example.MovieWeedsTest.config.jwt.ex.CustomException;
import com.example.MovieWeedsTest.domain.Genre;
import com.example.MovieWeedsTest.domain.Member;
import com.example.MovieWeedsTest.domain.MemberGenre;
import com.example.MovieWeedsTest.dto.request.RequestAuthMember;
import com.example.MovieWeedsTest.dto.request.RequestRecommendGenres;
import com.example.MovieWeedsTest.exception.NumberLimitException;
import com.example.MovieWeedsTest.exception.UserNotFoundException;
import com.example.MovieWeedsTest.repository.GenreRepository;
import com.example.MovieWeedsTest.repository.MemberGenreRepository;
import com.example.MovieWeedsTest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberGenreRepository memberGenreRepository;
    private final AuthenticationManager authenticationManager;
    private final GenreRepository genreRepository;

    public void newMemberCreate(RequestAuthMember requestAuthMember) {
        memberRepository.save(new Member(requestAuthMember, bCryptPasswordEncoder));
    }

    public TokenDto signInService(RequestAuthMember requestAuthMember) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestAuthMember.getEmail(), requestAuthMember.getPassword()));
            return new TokenDto(jwtTokenProvider.generateToken(authenticate));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid credentials supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void createRecommendedGenre(RequestRecommendGenres requestRecommendGenres, UserDetails userDetails) {
        Member member = getAuthenticationUser(userDetails);
        if (requestRecommendGenres.getGenres_id().size() > 3) {
            throw new NumberLimitException();
        }
        for (Long genre_id : requestRecommendGenres.getGenres_id()) {
            Genre genre  = genreRepository
                    .findById(genre_id)
                    .orElseThrow(() -> new EntityNotFoundException("해당 장르는 존재하지 않음"));
            memberGenreRepository.save(new MemberGenre(member, genre));
        }
    }

    public Member getAuthenticationUser(UserDetails userDetails) {
        return memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
    }



}
