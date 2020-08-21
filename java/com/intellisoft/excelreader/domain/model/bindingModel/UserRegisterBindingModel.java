package com.intellisoft.excelreader.domain.model.bindingModel;

import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {
    @NotNull
    private String email;


    private String password;


    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
