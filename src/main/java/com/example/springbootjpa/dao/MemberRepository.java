package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.MemberNameOnly;
import com.example.springbootjpa.dto.MemberQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member update(Member update);
    List<Member> search(MemberQueryParam queryParam, Pageable pageable);

    Page<Member> findMemberBy(Pageable pageable);
    Slice<Member> findMemberSliceBy(Pageable pageable);
    List<Member> findMemberListBy(Pageable pageable);

    List<MemberNameOnly> findProjectionByName(String name);

}
