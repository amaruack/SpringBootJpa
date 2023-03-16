package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.MemberRepository;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.dto.MemberCreateRequest;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.example.springbootjpa.dto.MemberResponse;
import com.example.springbootjpa.dto.MemberUpdateRequest;
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

    // 회원 수정
    public MemberResponse update(MemberUpdateRequest updateRequest) {
        Member entity = updateRequest.toEntity();
//        validateDuplicateMember(entity);
        Member updateEntity = repository.update(entity);
        return updateEntity.toResponse();
    }

    private void validateDuplicateMember(Member entity) {
        List<Member> search = repository.search(MemberQueryParam.builder().name(entity.getName()).build());
        if (!search.isEmpty()) {
            throw new IllegalArgumentException("duplicated name");
        }
    }

    public MemberResponse retrieve(Long id){
        return repository.findById(id).get().toResponse();
    }

    // 회원 전체 조회
    public List<MemberResponse> search(MemberQueryParam queryParam){
        return repository.search(queryParam).stream().map(Member::toResponse).collect(Collectors.toList());
    }


}
