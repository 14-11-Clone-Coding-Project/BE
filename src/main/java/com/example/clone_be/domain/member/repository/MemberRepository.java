package com.example.clone_be.domain.member.repository;

import com.example.clone_be.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    Boolean existsByMemberId(String memberId);
}
