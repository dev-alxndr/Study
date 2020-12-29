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
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("choi");

        // when
        Long savedId = memberService.join(member);

        // then
        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }


    @Test
    public void 중복_회원_예외() throws Exception {
        // given

        // when

        // then
    }
}