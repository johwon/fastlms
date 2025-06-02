package com.example.fastlms.member.model;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;

}
