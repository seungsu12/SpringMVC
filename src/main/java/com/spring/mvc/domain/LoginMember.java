package com.spring.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginMember {

    @NotEmpty
    private String loginId;


    @NotEmpty
    private String password;

}
