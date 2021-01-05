package io.alxndr.jpashop.service;

import io.alxndr.jpashop.domain.Member;
import io.alxndr.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MemberService {

    // Field injection
    private final MemberRepository memberRepository;

    /*
    다양한 Bean 주입 방법

    * case 1 : setter 주입 방법
    * -> 애플리케이션 실행시점에 변경해줄 일이 없기떄문에 case 2번이 더 좋다.
    *  @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    *
    * case 2: 생성자 주입방법
    * -> 생성자가 하나만 존재하는 경우 @Autowired를 제외해줘도 주입해준다.
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    *
    * case 3: Lombok 활용
    * @RequiredArgsConstructor -> final이 붙은 멤버 변수만 받는 생성자를 만들어줌
    * private final MemberRepository memberRepository;
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

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = memberRepository.findOne(id);
        findMember.setName(name);
    }
}
