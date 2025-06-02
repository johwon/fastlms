package com.example.fastlms.member.controller;

import com.example.fastlms.member.entity.Member;
import com.example.fastlms.member.model.MemberInput;
import com.example.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, MemberInput parameter){
        System.out.println(parameter.toString());

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/member/emailAuth")
    public String emailAuth(Model model,@RequestParam(name = "id") String uuid){
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);
        return "member/emailAuth";
    }


}
