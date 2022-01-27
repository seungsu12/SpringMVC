package com.spring.mvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class Member {

    private Long id;

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

}
