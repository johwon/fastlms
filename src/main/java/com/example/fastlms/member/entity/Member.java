package com.example.fastlms.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDate;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDate;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDate;
}
