package com.intellisoft.excelreader.domain.model.bindingModel;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserBindingModel {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;

    public UserBindingModel() {
    }

    private Set<String> roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
