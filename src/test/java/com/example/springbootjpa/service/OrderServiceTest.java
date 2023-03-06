package com.example.springbootjpa.service;

import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.item.Book;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.domain.valuetype.Address;
import com.example.springbootjpa.dto.OrderCancelRequest;
import com.example.springbootjpa.dto.OrderCreateRequest;
import com.example.springbootjpa.dto.OrderItemCreateRequest;
import com.example.springbootjpa.dto.OrderResponse;
import com.example.springbootjpa.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService service;

    @Test
    void 상품주문() {
        //given
        Member member = Member.builder()
                .name("son")
                .address(new Address("city", "street", "zipcode"))
                .build();
        em.persist(member);

        Item book = Book.builder()
                .name("springjpa")
                .stockQuantity(1_000)
                .price(10_000)
                .build();
        em.persist(book);

        em.flush();
        em.clear();

        //when
        OrderResponse response = service.save(OrderCreateRequest.builder()
                        .memberId(member.getId())
                        .orderItems(List.of(OrderItemCreateRequest.builder()
                                        .itemId(book.getId())
                                        .orderPrice(9_000)
                                        .count(10)
                                .build()))
                .build());

        // then
        Book find = em.find(Book.class, book.getId());
        OrderResponse findResponse = service.findById(response.getOrderId());

        assertEquals(990, find.getStockQuantity());
        assertEquals(OrderStatus.ORDER, findResponse.getOrderStatus());

    }

    @Test
    void 상품주문_제고_초과() {
        //given
        Member member = Member.builder()
                .name("son")
                .address(new Address("city", "street", "zipcode"))
                .build();
        em.persist(member);

        Item book = Book.builder()
                .name("springjpa")
                .stockQuantity(100)
                .price(10_000)
                .build();
        em.persist(book);

        em.flush();
        em.clear();

        //when
        NotEnoughStockException exception = assertThrows(NotEnoughStockException.class, () -> {
            OrderResponse response = service.save(OrderCreateRequest.builder()
                    .memberId(member.getId())
                    .orderItems(List.of(OrderItemCreateRequest.builder()
                            .itemId(book.getId())
                            .orderPrice(9_000)
                            .count(200)
                            .build()))
                    .build());
        });

        // then
        Book find = em.find(Book.class, book.getId());

        assertEquals(100, find.getStockQuantity());
        assertEquals("stock is small", exception.getMessage());

    }


    @Test
    void 주문취소() {
        //given
        Member member = Member.builder()
                .name("son")
                .address(new Address("city", "street", "zipcode"))
                .build();
        em.persist(member);

        Item book = Book.builder()
                .name("springjpa")
                .stockQuantity(1_000)
                .price(10_000)
                .build();
        em.persist(book);

        em.flush();
        em.clear();

        OrderResponse response = service.save(OrderCreateRequest.builder()
                .memberId(member.getId())
                .orderItems(List.of(OrderItemCreateRequest.builder()
                        .itemId(book.getId())
                        .orderPrice(9_000)
                        .count(10)
                        .build()))
                .build());

        //when
        service.cancel(OrderCancelRequest.builder()
                        .orderId(response.getOrderId())
                .build());

        // then
        Book find = em.find(Book.class, book.getId());
        OrderResponse findResponse = service.findById(response.getOrderId());

        assertEquals(1_000, find.getStockQuantity());
        assertEquals(OrderStatus.CANCEL, findResponse.getOrderStatus());

    }

    @Test
    void findById() {
    }

    @Test
    void 주문검색() {


    }
}