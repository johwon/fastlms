package com.example.fastlms.member.controller;

import com.example.fastlms.member.entity.Member;
import com.example.fastlms.member.model.MemberInput;
import com.example.fastlms.member.model.ResetPasswordInput;
import com.example.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @RequestMapping("/member/login")
    public String login(HttpServletRequest request, Model model) {
        // 세션에서 메시지 꺼내기
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            request.getSession().removeAttribute("errorMessage");  // 한 번만 보여주기 위해 삭제
        }
        return "member/login";
    }


    @GetMapping("/member/find/password")
    public String findPassword(){
        return "member/find_password";
    }


    @PostMapping("/member/find/password")
    public String register(Model model, ResetPasswordInput parameter){

        boolean result = false;
        try{
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }


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


    @GetMapping("/member/email_auth")
    public String emailAuth(Model model,@RequestParam(name = "id") String uuid){
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);
        return "member/email_auth";
    }


    @GetMapping("/member/info")
    public String memberInfo(){
        return "member/info";
    }

    @GetMapping("/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");
        model.addAttribute("uuid", uuid);

        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){
        boolean result = false;
        try{
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("result", result);

        return "member/reset_password_result";

    }

}
