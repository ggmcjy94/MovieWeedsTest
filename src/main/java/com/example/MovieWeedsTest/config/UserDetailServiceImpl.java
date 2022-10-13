package com.example.MovieWeedsTest.config;

import com.example.MovieWeedsTest.domain.Member;
import com.example.MovieWeedsTest.exception.UserNotFoundException;
import com.example.MovieWeedsTest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new User(member.getEmail(), member.getPassword(),grantedAuthorities);
    }
}
