package com.example.fastlms.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordInput {

    private String userId;
    private String userName;

    private String id;
    private String password;
}
