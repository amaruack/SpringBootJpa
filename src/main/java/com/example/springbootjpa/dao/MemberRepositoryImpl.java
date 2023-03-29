package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.QMember;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.google.common.base.Strings;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.example.springbootjpa.domain.QMember.member;

@Transactional
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl /*extends CrudRepository<Member, Long>*/ {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public Member update(Member update) {

        Member find = em.find(Member.class, update.getId());

        if(!Strings.isNullOrEmpty(update.getName())) {
            find.setName(update.getName());
        }

        return find;
    }

    public List<Member> search(MemberQueryParam queryParam, Pageable pageable) {
        List<Member> search = jpaQueryFactory
                .select(member)
                .from(member)
                .where(condition(member.name::eq, queryParam.getName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return search;
    }


    protected <T> OrderSpecifier[] orderCondition(Class<T> clazz, String variable, Pageable pageable) {
        PathBuilder<T> entityPath = new PathBuilder<>(clazz, variable);
        OrderSpecifier[] tmp = pageable.getSort()
                .stream()
                .map(order -> new OrderSpecifier(Order.valueOf(order.getDirection().name()), entityPath.get(order.getProperty())))
                .toArray(OrderSpecifier[]::new);
        return tmp ;
    }

    public BooleanExpression[] createWhere(MemberQueryParam queryParam){
        return new BooleanExpression[]{
                condition(member.name::eq, queryParam.getName()),
        };
    }

    protected <V> BooleanExpression condition(Function<V, BooleanExpression> function, V value) {
        return Optional.ofNullable(value).map(function).orElse(null);
    }

}
