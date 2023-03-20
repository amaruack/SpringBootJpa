package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberQueryParam;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
//@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void 맴버_저장_성공() {
        //given
        Member member = Member.builder()
                .name("son")
                .build();
        //when
        Member save = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(save.getId()).get();

        assertEquals(save.getId(), findMember.getId());
        assertEquals(save.getName(), findMember.getName());
        assertTrue(save.equals(findMember));

    }

    @Test
    void 맴버_조회_성공() {
        Member member = Member.builder()
                .name("son")
                .build();
        Member save = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(save.getId()).get();

        //then
        assertEquals(save.getId(), findMember.getId());
        assertEquals(save.getName(), findMember.getName());

        assertTrue(save.equals(findMember));
    }

    @Test
    void 맴버_검색_성공() {
        Member member = Member.builder()
                .name("son")
                .build();
        Member member2 = Member.builder()
                .name("son2")
                .build();

        memberRepository.save(member2);
        memberRepository.save(member);

        //when
        List<Member> search = memberRepository.search(MemberQueryParam.builder().name("son2").build());

        assertEquals(1, search.size());

    }


    @Test
    void 맴버_페이징_테스트_1() {

        memberRepository.save(Member.builder().name("son1").build());
        memberRepository.save(Member.builder().name("son2").build());
        memberRepository.save(Member.builder().name("son3").build());
        memberRepository.save(Member.builder().name("son4").build());
        memberRepository.save(Member.builder().name("son5").build());
        memberRepository.save(Member.builder().name("son6").build());

        //when
        Pageable pageable = PageRequest.of(0, 3);
        Page<Member> page = memberRepository.findMemberBy(pageable);
        Slice<Member> memberSlice = memberRepository.findMemberSliceBy(pageable);
        List<Member> memberList = memberRepository.findMemberListBy(pageable);

        System.out.println("sdf");

    }

}