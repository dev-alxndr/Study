package io.alxndr.jpashop.service;

import io.alxndr.jpashop.domain.Member;
import io.alxndr.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  // Test에 있을경우 Rollback 발생
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("choi");

        // when
        Long savedId = memberService.join(member);

        // then
        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("duplicate");

        Member duplicateMember = new Member();
        duplicateMember.setName("duplicate");

        // when
        memberService.join(member1);
        memberService.join(duplicateMember);

        // 아래처럼 안하고 expected 로 에러를 지정해주면 된다.
//        try {
//            memberService.join(duplicateMember);
//        } catch (IllegalStateException ise) {
//            return;
//        }

        // then
        Assert.fail("예외가 발생해야 한다.");    // 정상으로 작동하면 안된다. 위에 Exception 발생
    }
}