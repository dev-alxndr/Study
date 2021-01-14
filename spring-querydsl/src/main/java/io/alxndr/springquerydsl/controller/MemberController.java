package io.alxndr.springquerydsl.controller;

import io.alxndr.springquerydsl.dto.MemberSearchCondition;
import io.alxndr.springquerydsl.dto.MemberTeamDto;
import io.alxndr.springquerydsl.repository.MemberJpaRepository;
import io.alxndr.springquerydsl.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;


    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMEmbersV1(MemberSearchCondition condition) {
        return memberJpaRepository.searchByWhere(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMEmbersV2(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchWithPageSimple(condition, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMEmbersV3(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchWithPageComplex(condition, pageable);
    }
}
