package com.example.springbootjpa.controller.template;

import com.example.springbootjpa.dto.*;
import com.example.springbootjpa.service.ItemService;
import com.example.springbootjpa.service.MemberService;
import com.example.springbootjpa.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(value = "")
@Controller
@RequiredArgsConstructor
public class OrderController {

    final MemberService memberService;
    final ItemService itemService;
    final OrderService orderService;

    @GetMapping( value = "order")
    public String orderForm(Model model) {

        List<MemberResponse> members = memberService.search(MemberQueryParam.builder().build(), PageRequest.of(0,10));
        List<ItemResponse> items = itemService.search(ItemQueryParam.builder().build());

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping( value = "order")
    public String order(
            @Valid OrderCreateRequest orderCreateRequest,
            BindingResult result,
            Model model
    ) {
        OrderResponse response = orderService.save(orderCreateRequest);
        return "redirect:/orders";
    }

    @GetMapping( value = "orders")
    public String orderList(
            Model model ,
            @ModelAttribute("orderSearch") OrderQueryParam orderQueryParam
                            ) {
        model.addAttribute("orderSearch", new OrderQueryParam());
        List<OrderResponse> search = orderService.search(orderQueryParam, PageRequest.of(0, 10));
        model.addAttribute("orders", search);
        return "order/orderList";
    }

}
