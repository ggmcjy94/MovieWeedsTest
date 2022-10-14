package com.example.MovieWeedsTest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuthMember {

    @NotEmpty(message = "이메일을 입력 해주세요")
    @Email
    private String email;

    @NotEmpty(message = "패스워드를 입력해주세요")
    private String password;
}
