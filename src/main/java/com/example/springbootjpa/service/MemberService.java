package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.MemberRepository;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberCreateRequest;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.example.springbootjpa.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    public MemberResponse save(MemberCreateRequest createRequest) {
        Member entity = createRequest.toEntity();
        validateDuplicateMember(entity);
        Member saveEntity = memberRepository.save(entity);
        return saveEntity.toResponse();
    }

    private void validateDuplicateMember(Member entity) {
        List<Member> search = memberRepository.search(MemberQueryParam.builder().name(entity.getName()).build());
        if (!search.isEmpty()) {
            throw new IllegalArgumentException("duplicated name");
        }
    }

    public MemberResponse findById(Long id){
        return memberRepository.find(id).toResponse();
    }

    // 회원 전체 조회
    public List<MemberResponse> search(MemberQueryParam queryParam){
        return memberRepository.search(queryParam).stream().map(Member::toResponse).collect(Collectors.toList());
    }


}
