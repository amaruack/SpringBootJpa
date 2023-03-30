package com.example.springbootjpa.querydsl;

import com.example.springbootjpa.domain.Group;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.QGroup;
import com.example.springbootjpa.domain.QMember;
import com.example.springbootjpa.dto.MemberResponse;
import com.example.springbootjpa.dto.QMemberResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.example.springbootjpa.domain.QGroup.group;
import static com.example.springbootjpa.domain.QMember.member;
import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @Autowired
    EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;

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
                .age(10)
                .group(a)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .age(20)
                .group(a)
                .build();
        Member member3 = Member.builder()
                .name("member3")
                .age(40)
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

        Member findMember = query.select(member)
                .from(member)
                .where(member.name.eq("member1"))
                .fetchOne();

        assertThat(findMember.getName()).isEqualTo("member1");

    }


    @Test
    void 집합_테스트_aggregation(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> result = query.select(
                    member.count(),
                    member.age.sum(),
                    member.age.avg()
                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(3);
        assertThat(tuple.get(member.age.sum())).isEqualTo(70);

    }

    @Test
    void 집합_테스트_group(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> result = query.select(
                        group.name, member.age.avg()
                )
                .from(member)
                .join(member.group, group)
                .groupBy(group.name)
                .fetch();

        Tuple groupA = result.get(0);
        Tuple groupB = result.get(1);

    }

    @Test
    void 조인_테스트_일반(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        /**
         * 그룹 a 에 소속된 전체
         */
        List<Member> result = query.select(
                        member
                )
                .from(member)
//                .join(member.group, group).fetchJoin()
                .join(member.group, group)
                .where(group.name.eq("group_a"))
                .fetch();

        assertThat(result.size()).isEqualTo(2);

        assertThat(emf.getPersistenceUnitUtil().isLoaded(result.get(0).getGroup())).isTrue();

    }

    @Test
    void 세타_조인_테스트(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        em.persist(Member.builder().name("group_a").build());
        em.persist(Member.builder().name("group_b").build());


        List<Member> result = query.select(member)
                .from(member, group)
                .where(member.name.eq(group.name))
                .fetch();

        assertThat(result).extracting("name").containsExactly("group_a", "group_b");

    }

    @Test
    void 서브쿼리_테스트_by_where(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QMember membersub = new QMember("membersub");

        List<Member> result =
                query.select(member)
                .from(member)
                .where(member.age.eq(select(membersub.age.max()).from(membersub)))
                .fetch();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAge()).isEqualTo(40);
        assertThat(result).extracting("age").containsExactly(40);

    }

    @Test
    void 서브쿼리_테스트_by_select(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QMember membersub = new QMember("membersub");

        List<Tuple> result =
                query.select(member,
//                                Expressions.as(, ) // Expressions 로 서브쿼리 전체 alias 처리 가능
                                select(membersub.age.avg().as("aa")).from(membersub)
                        )
                        .from(member)
                        .fetch();
    }


    @Test
    void Case_테스트(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> result =
                query.select(member.age
                                .when(10).then("10살")
                                .when(20).then("20살")
                                .when(40).then("40살")
                                .otherwise("기타"),
                            new CaseBuilder()
                                    .when(member.age.between(10, 30)).then("어린이")
                                    .when(member.age.between(30, 40)).then("젊은이")
                                .otherwise("어른")
                        )
                        .from(member)
                        .fetch();


    }

    @Test
    void 상수_concat_테스트(){
        JPAQueryFactory query = new JPAQueryFactory(em);


        List<Tuple> result =
                query.select(member,
                                Expressions.constant("A")
                        , member.name.concat("_").concat(member.age.stringValue())
                        )
                        .from(member)
                        .fetch();

        for (Tuple tuple : result) {
            System.out.println(tuple.toString());
        }

    }


    /**  여기서 중급  */

    @Test
    void 프로젝션_테스트_tuple(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Tuple> result =
                query.select(member,
                                Expressions.constant("A")
                                , member.name.concat("_").concat(member.age.stringValue()).as("al")
                        )
                        .from(member)
                        .fetch();

        for (Tuple tuple : result) {
            System.out.println(tuple.toString());
            System.out.println(tuple.get(Expressions.constant("A")));
            System.out.println(tuple.get(member.name.concat("_").concat(member.age.stringValue()).as("al")));
        }

    }

    @Test
    void 프로젝션_테스트_dto(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        // setter 로 설정
        List<MemberResponse> result =
                query.select(Projections.bean(MemberResponse.class, member.id))
                        .from(member)
                        .fetch();

        for (MemberResponse tuple : result) {
            System.out.println(tuple.toString());
        }

    }

    @Test
    void 프로젝션_테스트_dto_field(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<MemberResponse> result =
                query.select(Projections.fields(MemberResponse.class, member.id, member.name, member.age))
                        .from(member)
                        .fetch();

        for (MemberResponse tuple : result) {
            System.out.println(tuple.toString());
        }
    }

    @Test
    void 프로젝션_테스트_dto_constructor(){
        JPAQueryFactory query = new JPAQueryFactory(em);

        // setter 로 설정
        List<MemberResponse> result =
                query.select(Projections.constructor(MemberResponse.class, member.id, member.name))
                        .from(member)
                        .fetch();

        for (MemberResponse tuple : result) {
            System.out.println(tuple.toString());
        }

    }

    @Test
    void 쿼리_프로젝션_테스트_dto_constructor(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        // setter 로 설정
        List<MemberResponse> result =
                query.select(new QMemberResponse(member.id, member.name))
                        .from(member)
                        .fetch();

        for (MemberResponse tuple : result) {
            System.out.println(tuple.toString());
        }

    }

    @Test
    void 동적쿼리_BooleanBuilder(){
        String username = "member1";
        Integer age = 10;

        List<Member> search = searchMember1(username, age);
        assertThat(search.size()).isEqualTo(1);

    }

    private List<Member> searchMember1(String username, Integer age) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (username != null) {
            booleanBuilder.and(member.name.eq(username));
        }
        if (age != null) {
            booleanBuilder.or(member.age.eq(age));
        }

        return query.select(member)
                .from(member)
                .where(booleanBuilder)
                .fetch();
    }

    @Test
    void 동적쿼리_where_param(){
        String username = "member1";
        Integer age = 10;

        List<Member> search = searchMember2(username, age);
        assertThat(search.size()).isEqualTo(1);

    }

    private List<Member> searchMember2(String username, Integer age) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        return query.select(member)
                .from(member)
                .where(
                        condition(member.name::eq, username),
                        condition(member.age::eq, age))
                .fetch();
    }

    private <T> BooleanExpression condition(Function<T, BooleanExpression> function, T username) {
        return Optional.ofNullable(username).map(function).orElse(null);
    }

    @Test
    void 벌크_연산(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        query.update(member)
                .set(member.name, "none")
                .where(member.age.lt(60))
                .execute();
    }


    @Test
    void method_reference_test(){

        TestMethodReference temp = new TestMethodReference();
        Function<Integer, String> tmp = temp::aaaa;

        Optional<Integer> testOption = Optional.of(10);
        System.out.println("!!!!!!");
        System.out.println(testOption.map(tmp).get());

    }


}
