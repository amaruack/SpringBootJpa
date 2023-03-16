package com.example.springbootjpa.dao;

import com.example.springbootjpa.domain.item.Book;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.ItemQueryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item update(Book updateData) ;

    List<Item> search(ItemQueryParam queryParam);

}
