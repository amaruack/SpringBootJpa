package com.example.springbootjpa.controller.api;

import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1.0/members")
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("")
    public Response search(
            @RequestParam(name = "name", required = false) String name,
            /*@ParameterObject*/ Pageable pageable
    ) {

        MemberQueryParam queryParam = MemberQueryParam.builder()
                .name(name)
                .build();

        List<MemberResponse> response = memberService.search(queryParam, pageable);

        return Response.builder()
                .responseCode("2000")
                .responseMessage("SUCCESS")
                .data(response)
                .build();

    }

    @PostMapping("")
    public MemberResponse save(
        @Validated @RequestBody MemberCreateRequest createRequest ) {

        MemberResponse response = memberService.save(createRequest);
        return response;

    }

    @GetMapping("{memberId}")
    public MemberResponse retrieve(
            @PathVariable("memberId") Long memberId) {

        MemberResponse response = memberService.retrieve(memberId);
        return response;
    }

    @PutMapping("{memberId}")
    public MemberResponse update(
            @PathVariable("memberId") Long memberId,
            @Validated @RequestBody MemberUpdateRequest updateRequest ) {

        updateRequest.setId(memberId);
        MemberResponse response = memberService.update(updateRequest);
        return response;
    }

}
