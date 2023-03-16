package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.google.common.base.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class MemberRepositoryImpl /*extends CrudRepository<Member, Long>*/ {

    @PersistenceContext
    private EntityManager em;

    public Member update(Member update) {

        Member find = em.find(Member.class, update.getId());

        if(!Strings.isNullOrEmpty(update.getName())) {
            find.setName(update.getName());
        }

        return find;
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
