package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.item.Book;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.ItemQueryParam;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ItemRepositoryImpl {

    private final EntityManager em;

    public Item update(Book updateData) {
        Book findData = (Book) em.find(Item.class, updateData.getId());

        if(!Strings.isNullOrEmpty(updateData.getName())){
            findData.setName(updateData.getName());
        }

        return findData;
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
