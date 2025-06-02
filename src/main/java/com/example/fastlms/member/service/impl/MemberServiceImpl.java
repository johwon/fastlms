package com.example.fastlms.member.service.impl;

import com.example.fastlms.components.MailComponents;
import com.example.fastlms.member.entity.Member;
import com.example.fastlms.member.model.MemberInput;
import com.example.fastlms.member.repository.MemberRepository;
import com.example.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            return false;
        }

        String uuid = UUID.randomUUID().toString();
        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .password(parameter.getPassword())
                .phone(parameter.getPhone())
                .regDate(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "FastLMS 사이트 가입을 축하드립니다.";
        String text = "<p>FastLMS 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하여 가입을 완료하세요</p>" +
                "<div><a href='http://localhost:8080/member/emailAuth?id="+uuid+"'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()){
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDate(LocalDateTime.now());

        memberRepository.save(member);
        return true;
    }
}
