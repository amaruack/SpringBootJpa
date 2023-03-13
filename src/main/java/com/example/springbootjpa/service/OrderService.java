package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.ItemRepository;
import com.example.springbootjpa.dao.MemberRepository;
import com.example.springbootjpa.dao.OrderRepository;
import com.example.springbootjpa.domain.*;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public OrderResponse save(OrderCreateRequest createRequest) {

        // 맴버 조회
        Member member = memberRepository.findById(createRequest.getMemberId());

        // 배송 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // 아이템 조회
        Item item = itemRepository.findById(createRequest.getItemId());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), createRequest.getCount());

        // 주문 생성
        Order entity = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        Order saveEntity = repository.save(entity);

        return saveEntity.toResponse();
    }

    @Transactional
    public OrderResponse saveList(OrderCreateRequestByItemList createRequest) {

        // 맴버 조회
        Member member = memberRepository.findById(createRequest.getMemberId());

        // 배송 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .build();

        // order item 생성
        List<OrderItem> orderItems = createRequest.getOrderItems().stream().map(orderItemCreateRequest -> {
            Item item = itemRepository.findById(orderItemCreateRequest.getItemId());
            return OrderItem.createOrderItem(item, orderItemCreateRequest.getOrderPrice(), orderItemCreateRequest.getCount());
        }).collect(Collectors.toList());

        // 주문 생성
        Order entity = Order.createOrder(member, delivery, orderItems);

        // 주문 저장
        Order saveEntity = repository.save(entity);

        return saveEntity.toResponse();
    }

    // 취소
    @Transactional
    public void cancel(OrderCancelRequest cancelRequest){
        Order order = repository.findById(cancelRequest.getOrderId());
        order.cancel();
    }

    // 조회
    public OrderResponse findById(Long id){
        return repository.findById(id).toResponse();
    }

    // 검색
    public List<OrderResponse> search(OrderQueryParam queryParam, Pageable pageable){
        return repository.search(queryParam, pageable).stream().map(Order::toResponse).collect(Collectors.toList());
    }

    // 검색 // TODO 잘 사용하지 않을듯
    public List<OrderQuery> searchOrderQuery(OrderQueryParam queryParam, Pageable pageable){
        return repository.searchOrderQuery(queryParam, pageable);
    }


}
