package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberCreateRequest;
import com.example.springbootjpa.dto.MemberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원_가입_성공() {

        //given
        MemberCreateRequest createRequest = MemberCreateRequest.builder()
                .name("son")
                .build();
        //when
        MemberResponse save = memberService.save(createRequest);

        //then
        MemberResponse findMember = memberService.retrieve(save.getId());

        assertEquals(save.getId(), findMember.getId());
        assertEquals(save.getName(), findMember.getName());

    }

    @Test()
    void 중복_회원_예외() {

        //given
        MemberCreateRequest createRequest = MemberCreateRequest.builder()
                .name("son")
                .build();

        MemberCreateRequest createRequest2 = MemberCreateRequest.builder()
                .name("son")
                .build();

        //when
        MemberResponse save1 = memberService.save(createRequest);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MemberResponse save2 = memberService.save(createRequest2);
        });

        //then
        assertEquals("duplicated name", exception.getMessage());

    }
}