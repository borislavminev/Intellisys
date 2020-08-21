package com.intellisoft.excelreader.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class UserDto {


    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String email;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String password;
    private String confirmPassword;


}
