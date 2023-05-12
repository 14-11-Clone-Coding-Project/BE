package com.example.clone_be.domain.member.controller;

import com.example.clone_be.domain.member.dto.LoginRequestDto;
import com.example.clone_be.domain.member.dto.SignupRequestDto;
import com.example.clone_be.domain.member.service.MemberService;
import com.example.clone_be.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.clone_be.util.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@RequestBody SignupRequestDto requestDto) {
        return memberService.signup(requestDto);
    }

    // 아이디 중복 확인
    @PostMapping("/checkId")
    public ResponseEntity<Message> checkId(Map<String, String> memberId) {
        return memberService.checkId(memberId);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        return memberService.login(requestDto, response);
    }


    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Message> logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request) {
        return memberService.logout(userDetails.getMember(), request);
    }
}
