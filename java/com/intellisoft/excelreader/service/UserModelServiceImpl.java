package com.intellisoft.excelreader.service;

import com.intellisoft.excelreader.domain.entity.Role;
import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserModelServiceImpl implements UserModelService {
    private final UsersRepository usersRepository;
    private final RoleModelServiceImpl roleModelService;
    private Set<Role> roles;

    @Autowired
    public UserModelServiceImpl(UsersRepository usersRepository, RoleModelServiceImpl roleModelService) {
        this.usersRepository = usersRepository;
        this.roleModelService = roleModelService;
    }

    @Override
    public void addUser(User user) {
        if (!usersRepository.findAll().contains(user))
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        if (usersRepository.findAll().contains(user))
            usersRepository.delete(user);
    }

    @Override
    public void setUserRole(String email, Role role) {

    }



    @Override
    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }




    @Override
    public List<User> findAllUser() {
        return usersRepository.findAll();
    }



    @Override
    public boolean findUserByEmail(String email){

       return usersRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void decreaseUserCredit(String email,Integer creditCharged) {
        User user = findByEmail(email).orElse(null);
        int currentCredit = user.getCredits();
        if (currentCredit<creditCharged){

            int creditNeed = creditCharged-currentCredit;
            throw new IllegalArgumentException("You need " + creditNeed + " more credits. To buy credits, visit your profile or contact us at support@intelisoft.eu." );

        }

        int decreaseCredit = currentCredit-creditCharged;
        user.setCredits(decreaseCredit);

    }

}
