package io.alxndr.springquerydsl.repository;

import io.alxndr.springquerydsl.dto.MemberSearchCondition;
import io.alxndr.springquerydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
