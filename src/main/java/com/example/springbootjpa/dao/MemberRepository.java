package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberQueryParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository /*extends CrudRepository<Member, Long>*/ {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> search(MemberQueryParam queryParam) {
        String query = "select m from Member m "
                + " where 1=1 ";
        if (queryParam.getName() != null) {
            query += " and m.name = :name";
        }
        TypedQuery<Member> typedQuery = em.createQuery(query, Member.class);
        if (queryParam.getName() != null) {
            typedQuery.setParameter("name", queryParam.getName());
        }

        return typedQuery.getResultList();
    }


}
