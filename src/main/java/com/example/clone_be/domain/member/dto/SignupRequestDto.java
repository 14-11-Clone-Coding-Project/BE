package com.example.clone_be.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    String memberId;
    String password;
}
