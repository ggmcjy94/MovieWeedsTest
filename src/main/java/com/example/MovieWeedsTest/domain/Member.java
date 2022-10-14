package com.example.MovieWeedsTest.domain;

import com.example.MovieWeedsTest.dto.request.RequestAuthMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email; // email

    @Column(nullable = false)
    private String password; // password

    @OneToMany(mappedBy = "member")
    private List<MemberGenre> memberGenres = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberMovie> memberMovies = new ArrayList<>();


    public Member(RequestAuthMember requestMember, PasswordEncoder bCryptPasswordEncoder) {
        this.email = requestMember.getEmail();
        this.password = bCryptPasswordEncoder.encode(requestMember.getPassword());

    }
}
