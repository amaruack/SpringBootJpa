package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository /*extends CrudRepository<Member, Long>*/ {

    @PersistenceContext
    private EntityManager em;

//    public List<Member>

    @Transactional
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
