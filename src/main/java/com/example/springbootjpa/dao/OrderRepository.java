package com.example.springbootjpa.dao;

import com.example.springbootjpa.config.OrderStatus;
import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.valuetype.Address;
import com.example.springbootjpa.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderRepository /*extends CrudRepository<Member, Long>*/ {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> search(OrderQueryParam queryParam, Pageable pageable) {

        String query = "select o from Order o join fetch o.member m "
                + " join fetch o.delivery d "
                + " where 1=1 ";

        if (queryParam.getName() != null) {
            query += " and o.name = :name";
        }

        if (queryParam.getOrderStatus() != null) {
            query += " and m.orderStatus = :orderStatus";
        }

        if (queryParam.getUserName() != null) {
            query += " and m.name = :userName";
        }

        TypedQuery<Order> typedQuery = em.createQuery(query, Order.class);

        if (queryParam.getName() != null) {
            typedQuery.setParameter("name", queryParam.getName());
        }

        if (queryParam.getOrderStatus() != null) {
            typedQuery.setParameter("orderStatus", queryParam.getOrderStatus());
        }

        if (queryParam.getUserName() != null) {
            typedQuery.setParameter("userName", queryParam.getUserName());
        }

        return typedQuery.getResultList();
    }

    public List<OrderQuery> searchOrderQuery(OrderQueryParam queryParam, Pageable pageable) {

        String query = "select new com.example.springbootjpa.dto.OrderQuery(o.id, o.orderDate, o.orderStatus, m.name, d.address) from Order o "
                + " join o.member m "
                + " join o.delivery d "
                + " where 1=1 ";

        if (queryParam.getName() != null) {
            query += " and o.name = :name";
        }

        if (queryParam.getOrderStatus() != null) {
            query += " and m.orderStatus = :orderStatus";
        }

        if (queryParam.getUserName() != null) {
            query += " and m.name = :userName";
        }

        TypedQuery<OrderQuery> typedQuery = em.createQuery(query, OrderQuery.class);

        if (queryParam.getName() != null) {
            typedQuery.setParameter("name", queryParam.getName());
        }

        if (queryParam.getOrderStatus() != null) {
            typedQuery.setParameter("orderStatus", queryParam.getOrderStatus());
        }

        if (queryParam.getUserName() != null) {
            typedQuery.setParameter("userName", queryParam.getUserName());
        }

        return typedQuery.getResultList();
    }


}
