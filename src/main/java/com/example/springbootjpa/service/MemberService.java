package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.MemberRepository;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberCreateRequest;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.example.springbootjpa.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    // 회원 가입
    public MemberResponse save(MemberCreateRequest createRequest) {
        Member entity = createRequest.toEntity();
        validateDuplicateMember(entity);
        Member saveEntity = repository.save(entity);
        return saveEntity.toResponse();
    }

    private void validateDuplicateMember(Member entity) {
        List<Member> search = repository.search(MemberQueryParam.builder().name(entity.getName()).build());
        if (!search.isEmpty()) {
            throw new IllegalArgumentException("duplicated name");
        }
    }

    public MemberResponse findById(Long id){
        return repository.findById(id).toResponse();
    }

    // 회원 전체 조회
    public List<MemberResponse> search(MemberQueryParam queryParam){
        return repository.search(queryParam).stream().map(Member::toResponse).collect(Collectors.toList());
    }


}
