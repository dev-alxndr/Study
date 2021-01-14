package io.alxndr.springquerydsl.controller;

import io.alxndr.springquerydsl.dto.MemberSearchCondition;
import io.alxndr.springquerydsl.dto.MemberTeamDto;
import io.alxndr.springquerydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;


    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMEmbersV1(MemberSearchCondition condition) {
        return memberJpaRepository.searchByWhere(condition);
    }

}
