package io.alxndr.springquerydsl.repository;

import io.alxndr.springquerydsl.dto.MemberSearchCondition;
import io.alxndr.springquerydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);

    Page<MemberTeamDto> searchWithPageSimple(MemberSearchCondition condition, Pageable pageable);

    Page<MemberTeamDto> searchWithPageComplex(MemberSearchCondition condition, Pageable pageable);
}
