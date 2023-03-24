package com.example.springbootjpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @Autowired
    EntityManager em;

    @Test
    void test(){
        JPAQueryFactory query = new JPAQueryFactory(em);


    }

}
