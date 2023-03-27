package com.example.springbootjpa.querydsl;

import com.example.springbootjpa.domain.Group;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach(){

        Group a = Group.builder()
                .name("group_a")
                .build();
        Group b = Group.builder()
                .name("group_b")
                .build();

        em.persist(a);
        em.persist(b);

        em.flush();
        em.clear();

        Member member1 = Member.builder()
                .name("member1")
                .group(a)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .group(a)
                .build();
        Member member3 = Member.builder()
                .name("member3")
                .group(b)
                .build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.flush();
    }

    @Test
    void jpaTest(){

//        assertThat()
    }

    @Test
    void queryDsl(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember m = QMember.member;

        Member findMember = query.select(m)
                .from(m)
                .where(m.name.eq("member1"))
                .fetchOne();

        assertThat(findMember.getName()).isEqualTo("member1");

    }

}
