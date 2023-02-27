package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberQueryParam;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void save() {
        //given
        Member member = Member.builder()
                .name("son")
                .build();
        //when
        Member save = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(save.getId());

        assertEquals(save.getId(), findMember.getId());
        assertEquals(save.getName(), findMember.getName());

        assertTrue(save.equals(findMember));

    }

    @Test
    void find() {
        Member member = Member.builder()
                .name("son")
                .build();
        Member save = memberRepository.save(member);

        //when
        Member findMember = memberRepository.find(save.getId());

        //then
        assertEquals(save.getId(), findMember.getId());
        assertEquals(save.getName(), findMember.getName());

        assertTrue(save.equals(findMember));
    }

    @Test
    void search() {
        Member member = Member.builder()
                .name("son")
                .build();
        Member member2 = Member.builder()
                .name("son2")
                .build();

        memberRepository.save(member2);
        memberRepository.save(member);

        //when
        List<Member> search = memberRepository.search(MemberQueryParam.builder().build());

        assertEquals(2, search.size());

    }

}