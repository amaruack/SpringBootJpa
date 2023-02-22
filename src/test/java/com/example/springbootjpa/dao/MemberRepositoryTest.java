package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    @Rollback(value = false)
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
    }
}