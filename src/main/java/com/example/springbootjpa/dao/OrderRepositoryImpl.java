package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.dto.OrderQueryParam;
import com.example.springbootjpa.dto.query.OrderQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Order> search(OrderQueryParam queryParam, Pageable pageable) {

        String query = "select distinct o from Order o "
                + " join fetch o.member m "
                + " join fetch o.delivery d "
                + " join o.orderItems oi "
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

        System.out.println(pageable.getOffset());
        System.out.println(pageable.getPageSize());
        return typedQuery
                .setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList()
                ;
    }

    // TODO 잘 사용하지 않을듯
    public List<OrderQuery> searchOrderQuery(OrderQueryParam queryParam, Pageable pageable) {

        String query = "select new com.example.springbootjpa.dto.query.OrderQuery(o.id, o.orderDate, o.orderStatus, m.name, d.address) from Order o "
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
