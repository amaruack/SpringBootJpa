package com.example.springbootjpa.controller.api;

import com.example.springbootjpa.dto.MemberQueryParam;
import com.example.springbootjpa.dto.MemberResponse;
import com.example.springbootjpa.dto.Response;
import com.example.springbootjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1.0/orders")
@RestController
public class OrderApiController {

    @Autowired
    OrderService service;

//    @GetMapping("")
//    public Response search(
//            @RequestParam(name = "name", required = false) String name) {
//
//        MemberQueryParam queryParam = MemberQueryParam.builder()
//                .name(name)
//                .build();
//
//        List<MemberResponse> response = memberService.search(queryParam);
//
//        return Response.builder()
//                .responseCode("2000")
//                .responseMessage("SUCCESS")
//                .data(response)
//                .build();
//
//    }

}
