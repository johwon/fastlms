package com.example.fastlms.main.controller;

import com.example.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index(){

        String email = "4971834@naver.com";
        String subject = "제목테스트";
        String text = "<p>안녕하세요</p><p>반갑습니다</p>";
//        mailComponents.sendMail(email, subject, text);
        return "index";
    }

}
