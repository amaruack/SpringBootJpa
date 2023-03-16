package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.dto.query.OrderQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> search(OrderQueryParam queryParam, Pageable pageable);
    List<OrderQuery> searchOrderQuery(OrderQueryParam queryParam, Pageable pageable);

}
