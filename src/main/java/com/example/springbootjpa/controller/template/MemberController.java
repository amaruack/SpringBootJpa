package com.example.springbootjpa.controller.template;

import com.example.springbootjpa.dto.MemberCreateRequest;
import com.example.springbootjpa.dto.MemberQueryParam;
import com.example.springbootjpa.dto.MemberResponse;
import com.example.springbootjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(value = "members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    final MemberService memberService;

    @GetMapping( value = "/new")
    public String newForm(Model model) {
        model.addAttribute("memberCreateRequest", new MemberCreateRequest());
        return "members/createMemberForm";
    }

    @GetMapping( value = "")
    public String list(Model model) {
        List<MemberResponse> members = memberService.search(MemberQueryParam.builder().build());
        model.addAttribute("members", members);
        return "members/memberList";
    }


    @PostMapping( value = "/new")
    public String create(
            @Valid MemberCreateRequest memberCreateRequest,
            BindingResult result,
            Model model
    ) {

        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        MemberResponse response = memberService.save(memberCreateRequest);
        return "redirect:/";
    }

}
