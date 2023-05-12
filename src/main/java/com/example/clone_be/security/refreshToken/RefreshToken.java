package com.example.clone_be.security.refreshToken;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private String memberId;

    public RefreshToken(String tokenDto,  String memberId) {
        this.refreshToken = tokenDto;
        this.memberId = memberId;
    }

    public RefreshToken updateToken(String tokenDto) {
        this.refreshToken = tokenDto;
        return this;
    }

}