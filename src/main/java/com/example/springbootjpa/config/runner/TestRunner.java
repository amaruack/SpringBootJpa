package com.example.springbootjpa.config.runner;

import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.service.ItemService;
import com.example.springbootjpa.service.MemberService;
import com.example.springbootjpa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testData1();
        testData2();
    }

    public void testData1() {
        MemberCreateRequest member1 = MemberCreateRequest.builder()
                .name("member1")
                .city("city1")
                .street("street1")
                .zipCode("zipcode1")
                .build();
        MemberResponse memberResponse1 = memberService.save(member1);

        BookCreateRequest book1 = BookCreateRequest.builder()
                .name("JPA1")
                .author("author")
                .isbn("i1111")
                .price(1_000)
                .stockQuantity(100)
                .build();
        BookCreateRequest book2 = BookCreateRequest.builder()
                .name("JPA2")
                .author("author")
                .isbn("i1111")
                .price(1_000)
                .stockQuantity(100)
                .build();

        ItemResponse bookResponse1 = itemService.save(book1);
        ItemResponse bookResponse2 = itemService.save(book2);

        OrderCreateRequestByItemList order = OrderCreateRequestByItemList.builder()
                .memberId(memberResponse1.getId())
                .orderItems(List.of(
                        OrderItemCreateRequest.builder().itemId(bookResponse1.getId())
                                .count(10)
                                .orderPrice(bookResponse1.getPrice())
                                .build(),
                        OrderItemCreateRequest.builder().itemId(bookResponse2.getId())
                                .count(10)
                                .orderPrice(bookResponse2.getPrice())
                                .build()
                ))
                .build();

        orderService.saveList(order);
    }

    public void testData2() {
        MemberCreateRequest member1 = MemberCreateRequest.builder()
                .name("member2")
                .city("city2")
                .street("street2")
                .zipCode("zipcode2")
                .build();
        MemberResponse memberResponse1 = memberService.save(member1);

        BookCreateRequest book1 = BookCreateRequest.builder()
                .name("SPRING1")
                .author("author")
                .isbn("i1111")
                .price(1_000)
                .stockQuantity(100)
                .build();
        BookCreateRequest book2 = BookCreateRequest.builder()
                .name("SPRING2")
                .author("author")
                .isbn("i1111")
                .price(1_000)
                .stockQuantity(100)
                .build();

        ItemResponse bookResponse1 = itemService.save(book1);
        ItemResponse bookResponse2 = itemService.save(book2);

        OrderCreateRequestByItemList order = OrderCreateRequestByItemList.builder()
                .memberId(memberResponse1.getId())
                .orderItems(List.of(
                        OrderItemCreateRequest.builder().itemId(bookResponse1.getId())
                                .count(10)
                                .orderPrice(bookResponse1.getPrice())
                                .build(),
                        OrderItemCreateRequest.builder().itemId(bookResponse2.getId())
                                .count(10)
                                .orderPrice(bookResponse2.getPrice())
                                .build()
                ))
                .build();

        orderService.saveList(order);
    }
}
