package com.example.springbootjpa.dao;

import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.dto.query.OrderQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> search(OrderQueryParam queryParam, Pageable pageable);
    List<OrderQuery> searchOrderQuery(OrderQueryParam queryParam, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"member"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"member"})
    List<Order> findEntityGraphByOrderStatus(OrderStatus orderStatus);

    // TODO 이건 좀 더 공부해야할듯
    @Lock(LockModeType.OPTIMISTIC)
    List<Order> findLockByOrderStatus(OrderStatus orderStatus);


}
