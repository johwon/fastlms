package com.example.fastlms.member.service;

import com.example.fastlms.member.model.MemberInput;

public interface MemberService {

    boolean register(MemberInput parameter);

    boolean emailAuth(String uuid);
}
