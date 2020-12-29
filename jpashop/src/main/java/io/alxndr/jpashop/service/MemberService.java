package io.alxndr.jpashop.service;

import io.alxndr.jpashop.domain.Member;
import io.alxndr.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* Class 계층에 readOnly를 주고 Transaction이 필요한 추가,수정등은 따로 추가해준다.
* Method 계층이 우선권을 가지고 readOnly = false로 동작하게 된다.
*
* readOnly true 읽기 성능 최적화  // DB 읽기전용, Entity Dirty Check 안함
* */

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired  // Field injection
    private MemberRepository memberRepository;

    // 다양한 Bean 주입 방법
    /*
    * case 1 : setter 주입 방법
    *  @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    *
    * */




    /*
     회원가입
    */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);  // Persist되면 PK:Id가 존재한다는걸 보증함.
        return member.getId();

    }

    /*
    중복 검사
    */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.") ;
        }
    }

    /*
    회원전체 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
     회원 단건 조회
    */
    public Member findOne(Member member) {

        return memberRepository.findOne(member.getId());
    }

}
