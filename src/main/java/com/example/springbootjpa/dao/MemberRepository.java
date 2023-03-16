package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberQueryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member update(Member update);
    List<Member> search(MemberQueryParam queryParam);

}
