package com.example.springbootjpa.controller.template;

import com.example.springbootjpa.domain.item.Book;
import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.service.BookService;
import com.example.springbootjpa.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(value = "items")
@Controller
@RequiredArgsConstructor
public class ItemController {

    final ItemService itemService;

    @GetMapping( value = "/new")
    public String newForm(Model model) {
        model.addAttribute("form", new BookCreateRequest());
        return "items/createItemForm";
    }

    @PostMapping( value = "/new")
    public String create(
            @Valid BookCreateRequest createRequest,
            BindingResult result,
            Model model ) {

        if(result.hasErrors()) {
            return "items/createItemForm";
        }

        ItemResponse response = itemService.save(createRequest);
        return "redirect:/items";
    }

    @GetMapping( value = "")
    public String list(Model model) {
        List<ItemResponse> items = itemService.search(ItemQueryParam.builder().build());
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping( value = "{id}/edit")
    public String editForm(
            @PathVariable(name = "id") Long id,
            Model model
    ) {

        ItemResponse item = itemService.findById(id);
        model.addAttribute("form", item);

        return "items/updateItemForm";
    }

    @PostMapping( value = "{id}/edit")
    public String edit(
            @PathVariable(name = "id") Long id,
            @Valid BookUpdateRequest updateRequest,
            Model model
    ) {
        updateRequest.setId(id);
        itemService.update(updateRequest);
        return "redirect:/items";
    }

//    @PutMapping( value = "/new")
//    public String create(
//            @Valid BookCreateRequest createRequest,
//            BindingResult result,
//            Model model
//    ) {
//
//        if(result.hasErrors()) {
//            return "items/createItemForm";
//        }
//
//        ItemResponse response = itemService.save(createRequest);
//        return "redirect:/items";
//    }

}
