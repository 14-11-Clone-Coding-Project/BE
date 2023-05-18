package com.example.clone_be.domain.member.service;

import com.example.clone_be.Redis.util.RedisUtil;
import com.example.clone_be.domain.chat.util.BadWordFiltering;
import com.example.clone_be.domain.chat.util.BadWords;
import com.example.clone_be.domain.member.dto.LoginRequestDto;
import com.example.clone_be.domain.member.dto.SignupRequestDto;

import com.example.clone_be.domain.member.entity.Member;
import com.example.clone_be.domain.member.repository.MemberRepository;
import com.example.clone_be.exception.CustomException;
import com.example.clone_be.exception.ErrorCode;
import com.example.clone_be.security.jwt.JwtUtil;
import com.example.clone_be.security.refreshToken.RefreshTokenRepository;
import com.example.clone_be.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements BadWords {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    private final BadWordFiltering badWordFiltering;

    // 회원가입
    public ResponseEntity<Message> signup(SignupRequestDto requestDto) {
        String memberId = requestDto.getMemberId();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Member member = new Member(memberId, password);
        memberRepository.save(member);
        return new ResponseEntity<>(new Message("회원가입 성공", null), HttpStatus.OK);
    }

    // 중복 체크
    public ResponseEntity<Message> checkId(Map<String, String> memberId) {
        String input = memberId.get("memberId");
        if (input == null || input.replace(" ","").equals("")) {
            return new ResponseEntity<>(new Message("아이디를 입력해주세요.", null), HttpStatus.BAD_REQUEST);
        } else if (badWordFiltering.checkBadId(input)) {
            return new ResponseEntity<>(new Message("혼나!", null), HttpStatus.BAD_REQUEST);
        } else if (memberRepository.existsByMemberId(input)) {
            return new ResponseEntity<>(new Message("사용 중인 아이디입니다.", null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message("사용 가능한 아이디입니다.", null), HttpStatus.OK);
    }

    // 로그인
    public ResponseEntity<Message> login(LoginRequestDto requestDto, HttpServletResponse response) {

        // 사용자 확인
        Member member = memberRepository.findByMemberId(requestDto.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 확인
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        response.addHeader(JwtUtil.ACCESS_KEY, jwtUtil.createToken(member.getMemberId(), "Access"));
        return new ResponseEntity<>(new Message("로그인 성공", member.getMemberId()), HttpStatus.OK);
    }

    // 로그아웃 - 현재 프로젝트에서는 사용되고 있지 않음
    public ResponseEntity<Message> logout(HttpServletRequest request) {
//        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberId(member.getMemberId());

        String accessToken = request.getHeader("ACCESS_KEY").substring(7);
//        if(refreshToken.isPresent()){
        Long tokenTime = jwtUtil.getExpirationTime(accessToken);
        redisUtil.setBlackList(accessToken, "access_token", tokenTime);
//        refreshTokenRepository.deleteByMemberId(member.getMemberId());
        return new ResponseEntity<>(new Message("로그아웃 성공", null), HttpStatus.OK);
//        }
//        return new ResponseEntity(new Message("로그아웃 실패", null), HttpStatus.BAD_REQUEST);
    }
}
