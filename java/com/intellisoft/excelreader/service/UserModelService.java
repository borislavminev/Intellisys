package com.intellisoft.excelreader.service;

import com.intellisoft.excelreader.domain.entity.Role;
import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.domain.model.bindingModel.UserBindingModel;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserModelService {

    void addUser(User user);
    void deleteUser(User user);
    void setUserRole(String email, Role role);
    void setRole(Set<Role> roles);
    List <User> findAllUser();
    boolean findUserByEmail(String email);
    Optional<User> findByEmail(String email);
    //Each download decreasing credit
    void decreaseUserCredit(String email,Integer creditCharged);

}
