package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.ItemRepository;
import com.example.springbootjpa.domain.item.Item;
import com.example.springbootjpa.dto.ItemCreateRequest;
import com.example.springbootjpa.dto.ItemQueryParam;
import com.example.springbootjpa.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final ItemRepository repository;

    @Transactional
    public ItemResponse save(ItemCreateRequest createRequest) {
        Item entity = createRequest.toEntity();
        Item saveEntity = repository.save(entity);
        return saveEntity.toResponse();
    }

    public ItemResponse findById(Long id){
        return repository.findById(id).toResponse();
    }

    public List<ItemResponse> search(ItemQueryParam queryParam){
        return repository.search(queryParam).stream().map(Item::toResponse).collect(Collectors.toList());
    }


}
