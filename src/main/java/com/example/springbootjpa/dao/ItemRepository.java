package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.Member;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.ItemQueryParam;
import com.example.springbootjpa.dto.MemberQueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    @Transactional
    public Item save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
        return item;
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> search(ItemQueryParam queryParam) {
        String query = "select i from Item i "
                + " where 1=1 ";
        if (queryParam.getName() != null) {
            query += " and i.name = :name";
        }
        TypedQuery<Item> typedQuery = em.createQuery(query, Item.class);
        if (queryParam.getName() != null) {
            typedQuery.setParameter("name", queryParam.getName());
        }

        return typedQuery.getResultList();
    }

}
