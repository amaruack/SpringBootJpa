package com.example.springbootjpa.controller.api;

import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.dto.query.OrderQuery;
import com.example.springbootjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("")
@RestController
public class OrderApiController {

    @Autowired
    OrderService service;

    @GetMapping("/api/v1.0/orders")
    public Response search(
            @RequestParam(name = "name", required = false) String name,
            Pageable pageable
    ) {

        OrderQueryParam queryParam = OrderQueryParam.builder()
                .name(name)
                .build();

        List<OrderResponse> response = service.search(queryParam, pageable);

        return Response.builder()
                .responseCode("2000")
                .responseMessage("SUCCESS")
                .data(response)
                .build();

    }

    //TODO 잘 사용을 하지 않을듯
    @GetMapping("/api/v1.1/orders")
    public Response searchv11(
            @RequestParam(name = "name", required = false) String name,
            Pageable pageable
    ) {
        OrderQueryParam queryParam = OrderQueryParam.builder()
                .name(name)
                .build();
        List<OrderQuery> response = service.searchOrderQuery(queryParam, pageable);

        return Response.builder()
                .responseCode("2000")
                .responseMessage("SUCCESS")
                .data(response)
                .build();

    }

}
